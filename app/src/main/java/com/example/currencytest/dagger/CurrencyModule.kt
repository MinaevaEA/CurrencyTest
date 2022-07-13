import android.content.Context
import androidx.room.Room
import com.example.currencytest.currency.CurrencyInteract
import com.example.currencytest.db.AppDatabase
import com.example.currencytest.retrofit.RetrofitServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class CurrencyModule {
    @Provides
    fun getCurrencyInteractor(
        dataConcreteCurrency: RetrofitServices,
        database: AppDatabase
    ): CurrencyInteract {
        val interact = CurrencyInteract(dataConcreteCurrency, database)
        return interact
    }
}