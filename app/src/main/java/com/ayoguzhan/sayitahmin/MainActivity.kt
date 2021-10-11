package com.ayoguzhan.sayitahmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ayoguzhan.sayitahmin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var tutulanSayi = 0
    var hak = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(this, "Öncelikle sayı tut'a basınız", Toast.LENGTH_SHORT).show()


        binding.hak.text = "Hak: ${hak}"
        
        binding.sonucGor.setOnClickListener {
            hak -= 1
            binding.hak.text = "Hak: ${hak}"
            if (hak == 0 ){
                Toast.makeText(this, "Hakkınız dolmuştur tekrardan sayı tut'a basınız.", Toast.LENGTH_SHORT).show()
                binding.sonucGor.isEnabled = false
            }
            sayiTahmin()
        }
        binding.sayiTut.setOnClickListener {
            hak =5
            binding.hak.text = "Hak: ${hak}"
            binding.sonucGor.isEnabled = true
            var secilenRadioButton = binding.sayiAralik.checkedRadioButtonId
            tutulanSayi = when(secilenRadioButton){
                R.id.sifirYirmibes -> (1..25).random()
                R.id.sifirElli -> (1..50).random()
                else -> (1..100).random()
            }
            var ipucuDurum = binding.ipucu.isChecked

            if (ipucuDurum){
                Toast.makeText(this, "Tutulan sayının karesi ${tutulanSayi *tutulanSayi}", Toast.LENGTH_SHORT).show()
            }
            
        }
    }   

    fun sayiTahmin(){
        var tahmin = binding.tahminEditText.text.toString()

        var tahminYeni = tahmin.toDoubleOrNull()

        if(tahminYeni == null ){
            binding.sonucText.text = "Sonuç: \uD83D\uDE80"
            return
        }

        if(tahmin == tutulanSayi.toString()){
            binding.sonucText.text = "Tebrikler doğru bildiniz"

        }else
            binding.sonucText.text = "Tekrar deneyiniz"
    }
}