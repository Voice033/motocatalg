package jp.co.planaria.sample.motocatalog.forms;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jp.co.planaria.sample.motocatalog.beans.Brand;
import lombok.Data;

/**
 * 更新画面の入力内容
 */
@Data
public class MotoForm {
    // バイク番号
    private Integer motoNo;
    // バイク名
    @NotBlank
    @Size(min = 1, max = 50)
    private String motoName;
    // シート高
    @Min(0)
    @Max(1000)
    private Integer seatHeight;
    // シリンダー
    @Min(1)
    @Max(4)
    private Integer cylinder;
    // 冷却
    @Size(max = 10)
    private String cooling;
    // 価格
    @Min(10000)
    private Integer price;
    // コメント
    @Size(max = 200)
    private String comment;
    // ブランドID
    @Valid
    private Brand brandId;
    // バージョン
    private Integer version;

}
