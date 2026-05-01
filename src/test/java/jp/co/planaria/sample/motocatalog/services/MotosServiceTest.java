package jp.co.planaria.sample.motocatalog.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import jp.co.planaria.sample.motocatalog.beans.Brand;
import jp.co.planaria.sample.motocatalog.beans.Motorcycle;
import jp.co.planaria.sample.motocatalog.beans.SearchForm;

@SpringBootTest
public class MotosServiceTest {

    // 処理の時間差でassertFailになるため分までを確認
    DateTimeFormatter dtFormater = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    @Autowired
    MotosService service;

    // @Test
    // void バイク情報を全件検索できる() {
    // SearchCondition condition = new SearchCondition();
    // List <Motorcycle> motos = service.getMotos(condition);
    // 検索結果の件数確認
    // assertThat(motos.size()).isEqualTo(2);
    // 検索結果の各項目の確認
    // Motorcycle moto = motos.get(0); //1件目
    // assertThat(moto).isNotNull();
    // assertThat(moto.getMotoNo()).isEqualTo(1);
    // assertThat(moto.getMotoName()).isEqualTo("GB350");
    // assertThat(moto.getPrice()).isEqualTo(550000);
    // assertThat(moto.getBrandId().getBrandName()).isEqualTo("Honda");
    // }

    // バイク一覧取得 条件: ブランドID
    @DisplayName("バイク一覧取得 条件: ブランドID")
    @ParameterizedTest
    @CsvSource({ "01, Honda", "02, Kawasaki", "03, Yamaha" })
    void test001(String brandId, String brandName) {
        SearchForm condition = new SearchForm();
        condition.setBrandId(brandId);

        List<Motorcycle> motos = service.getMotos(condition); // テスト対象
        assertThat(motos.size()).isGreaterThanOrEqualTo(1); // 1以上
        for (Motorcycle moto : motos) {
            assertThat(moto.getBrandId().getBrandName()).isEqualTo(brandName);
        }
    }

    @DisplayName("バイク一覧取得 条件: ブランドID 該当なし")
    @Test
    void test002() {
        SearchForm condition = new SearchForm();
        condition.setBrandId("99");

        List<Motorcycle> motos = service.getMotos(condition); // テスト対象
        assertThat(motos.size()).isEqualTo(0);
    }

    @DisplayName("バイク一覧取得 条件: バイク名-完全一致")
    @ParameterizedTest
    @CsvSource({ "Z900RS CAFE", "YZF-R1", "Rebel250" })
    void test003(String motoName) {
        SearchForm condition = new SearchForm();
        condition.setKeyword(motoName);

        List<Motorcycle> motos = service.getMotos(condition); // テスト対象
        assertThat(motos.size()).isGreaterThanOrEqualTo(1); // 1以上
        for (Motorcycle moto : motos) {
            assertThat(moto.getMotoName()).isEqualTo(motoName);
        }
    }

    @DisplayName("バイク一覧取得 条件: バイク名-部分一致")
    @ParameterizedTest
    @CsvSource({ "Z900RS CA, Z900RS CAFE", "F-R1, YZF-R1", "bel25, Rebel250" })
    void test004(String keyword, String motoName) {
        SearchForm condition = new SearchForm();
        condition.setKeyword(keyword);

        List<Motorcycle> motos = service.getMotos(condition); // テスト対象
        assertThat(motos.size()).isGreaterThanOrEqualTo(1); // 1以上
        for (Motorcycle moto : motos) {
            assertThat(moto.getMotoName()).isEqualTo(motoName);
        }
    }

    @DisplayName("バイク一覧取得 条件: バイク名-該当なし")
    @Test
    void test005() {
        SearchForm condition = new SearchForm();
        condition.setKeyword("存在しないバイク名");

        List<Motorcycle> motos = service.getMotos(condition); // テスト対象
        assertThat(motos.size()).isEqualTo(0);
    }

    @DisplayName("バイク一覧取得 条件: ブランドID, バイク名")
    @ParameterizedTest
    @CsvSource({ "02, Z900, Z900RS", "03, R1, YZF-R1", "01, bel, Rebel" })
    void test006(String brandId, String keyword, String motoName) {
        SearchForm condition = new SearchForm();
        condition.setBrandId(brandId);
        condition.setKeyword(keyword);

        List<Motorcycle> motos = service.getMotos(condition); // テスト対象
        assertThat(motos.size()).isGreaterThanOrEqualTo(1); // 1以上
        for (Motorcycle moto : motos) {
            assertThat(moto.getMotoName()).startsWith(motoName);
        }
    }

    @DisplayName("バイク一覧取得 条件: ブランドID,バイク名-該当なし")
    @ParameterizedTest
    @CsvSource({ "01, Z900RS", "03, R99" })
    void test007(String brandId, String keyword) {
        SearchForm condition = new SearchForm();
        condition.setBrandId(brandId);
        condition.setKeyword(keyword);

        List<Motorcycle> motos = service.getMotos(condition); // テスト対象
        assertThat(motos.size()).isEqualTo(0);
    }

    @DisplayName("バイク一覧取得 条件: なし 全件該当")
    @Test
    void test008() {
        SearchForm condition = new SearchForm();

        List<Motorcycle> motos = service.getMotos(condition); // テスト対象
        assertThat(motos.size()).isEqualTo(9);
    }

    // バイク一覧取得 条件: バイク番号
    @DisplayName("バイク一覧取得 条件: バイク番号")
    @ParameterizedTest
    @CsvSource({ "1, GB350", "3, W800 CAFE" })
    void test009(int motoNo, String motoName) {

        Motorcycle moto = service.getMotos(motoNo); // テスト対象
        assertThat(moto.getMotoName()).isEqualTo(motoName);
    }

    @DisplayName("バイク一覧取得 条件: バイク番号 全項目確認")
    @Test
    void test0010() {
        Motorcycle moto = service.getMotos(1); // テスト対象
        assertThat(moto.getMotoNo()).isEqualTo(1);
        assertThat(moto.getMotoName()).isEqualTo("GB350");
        assertThat(moto.getSeatHeight()).isEqualTo(800);
        assertThat(moto.getCylinder()).isEqualTo(1);
        assertThat(moto.getCooling()).isEqualTo("空冷");
        assertThat(moto.getPrice()).isEqualTo(550000);
        assertThat(moto.getComment()).isEqualTo("エンジンのとことこ音がいい");
        assertThat(moto.getBrandId().getBrandId()).isEqualTo("01");
        assertThat(moto.getVersion()).isEqualTo(1);
        assertThat(moto.getInsDt().format(dtFormater)).isEqualTo(LocalDateTime.now().format(dtFormater));
        assertThat(moto.getUpdDt()).isNull();
    }

    @DisplayName("バイク情報更新")
    @Test
    @Transactional
    @Rollback
    void test0011() {
        Motorcycle before = service.getMotos(1);
        before.setMotoName("motomoto");

        service.save(before); // 更新（保存）

        Motorcycle after = service.getMotos(1); // 変更後のバイク情報取得
        assertThat(after.getMotoName()).isEqualTo("motomoto");
        assertThat(after.getVersion()).isEqualTo(before.getVersion() + 1);
    }

    @DisplayName("バイク情報登録")
    @Test
    @Transactional
    @Rollback
    void test0012() {
        Motorcycle before = new Motorcycle();
        // バイク名
        before.setMotoName("あたらしい");
        // シート高
        before.setSeatHeight(100);
        // シリンダー
        before.setCylinder(1);
        // 冷却
        before.setCooling("冷凍");
        // 価格
        before.setPrice(1000);
        // コメント
        before.setComment("コメント");
        // ブランドID
        before.setBrandId(new Brand("01", "Honda"));

        service.save(before); // 登録（保存）

        Motorcycle after = service.getMotos(10); // 変更後のバイク情報取得
        assertThat(after.getMotoNo()).isEqualTo(10);
        assertThat(after.getMotoName()).isEqualTo("あたらしい");
        assertThat(after.getSeatHeight()).isEqualTo(100);
        assertThat(after.getCylinder()).isEqualTo(1);
        assertThat(after.getCooling()).isEqualTo("冷凍");
        assertThat(after.getPrice()).isEqualTo(1000);
        assertThat(after.getComment()).isEqualTo("コメント");
        assertThat(after.getBrandId().getBrandId()).isEqualTo("01");
        assertThat(after.getVersion()).isEqualTo(1);
        //assertThat(after.getInsDt().format(dtFormater)).isEqualTo(LocalDateTime.now().format(dtFormater));
        assertThat(after.getUpdDt()).isNull();
    }

    @DisplayName("バイク情報削除")
    @Test
    @Transactional
    @Rollback
    void test0013() {
        Motorcycle before = service.getMotos(1);

        service.delete(before); // 削除

        Motorcycle after = service.getMotos(1); // 変更後のバイク情報取得
        assertThat(after).isNull();
    }

}
