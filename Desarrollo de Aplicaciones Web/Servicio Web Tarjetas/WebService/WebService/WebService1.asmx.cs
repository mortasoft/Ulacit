using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Data.SqlClient;
using System.Data;

namespace WebService
{
    /// <summary>
    /// Descripción breve de WebService1
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // Para permitir que se llame a este servicio web desde un script, usando ASP.NET AJAX, quite la marca de comentario de la línea siguiente. 
    // [System.Web.Script.Services.ScriptService]
    public class WebService1 : System.Web.Services.WebService
    {
        SqlConnection conn;
        string sqlConnectionString = "Data Source=FREYA;Integrated Security=True;Connect Timeout=30;Encrypt=False;TrustServerCertificate=False;ApplicationIntent=ReadWrite;MultiSubnetFailover=False";

        [WebMethod]
        public string CompraTarjetaCreditoDebito(Int64 Num_Tarjeta, int Mes_Exp, int Año_Exp, int CVV, Decimal Monto, string Tipo)
        {
            if (existeTarjeta(Num_Tarjeta))
            {
                if (fechaExpiracionInvalida(Num_Tarjeta, Mes_Exp, Año_Exp))
                {
                    if (cvvValido(Num_Tarjeta, CVV))
                    {
                        if (fondosSuficientes(Num_Tarjeta, Monto))
                        {
                            return "-0;Transacción Exitosa";
                        }
                        else {
                            return "-4;Fondos Insuficientes";
                        }

                    }
                    else {
                        return "-3;CVV Incorrecto";
                    }

                }
                else {
                    return "-2;Fecha de expiración inválida o la tarjeta expiró";
                }

            }
            else {
                return "-1;Número de tarjeta Inválido";
            }

        }

        [WebMethod]
        public string CompraEasyPay(int Num_Cuenta, int Codigo_Seguridad, string Contraseña, decimal Monto) {

            if (existeCuenta(Num_Cuenta))
            {
                if (codigoSeguridadExiste(Num_Cuenta, Codigo_Seguridad)) {
                    if (contraseñaValida(Num_Cuenta, Contraseña))
                    {
                        if (fondosSuficientesEasyPay(Num_Cuenta, Monto))
                        {
                            return "-0;Transacción Exitosa";
                        }
                        else {
                            return "-4;Fondos Insuficientes";
                        }
                    }
                    else{
                        return "-3;Contraseña Inválida";
                    }
                }
                else{
                    return "-2;Código de seguridad Inválido";
                }
            }
            else {
                return "-1;Número de cuenta inválido";
            }

            return "";
        }

        private Boolean existeCuenta(int Num_Cuenta) {
            System.Data.DataTable dt = new System.Data.DataTable();

            try
            {
                conn = new SqlConnection();
                // Hay que cambiar el Connection String de acuerdo a la PC
                conn.ConnectionString = sqlConnectionString;
                string sql = @"select * from [SW_Tarjetas].[dbo].[Compra_EasyPay] where num_cuenta=@Num_Cuenta";

                SqlCommand cmd = new SqlCommand(sql, conn);

                //Defino parametros y sus caracteristicas
                SqlParameter Num_Cuentaparametro = new SqlParameter();
                Num_Cuentaparametro.SqlDbType = System.Data.SqlDbType.BigInt;
                Num_Cuentaparametro.ParameterName = "@Num_Cuenta";
                Num_Cuentaparametro.Value = Num_Cuenta;
                cmd.Parameters.Add(Num_Cuentaparametro);

                // Llena los datos
                SqlDataAdapter da = new SqlDataAdapter(cmd);

                da.Fill(dt);

                if (dt.Rows.Count > 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }

            }
            catch (Exception ex)
            {
                return false;
            }
        }

        private Boolean codigoSeguridadExiste(int Num_Cuenta, int Codigo_Seguridad) {
            System.Data.DataTable dt = new System.Data.DataTable();

            try
            {
                conn = new SqlConnection();
                // Hay que cambiar el Connection String de acuerdo a la PC
                conn.ConnectionString = sqlConnectionString;
                string sql = @"select * from [SW_Tarjetas].[dbo].[Compra_EasyPay] where num_cuenta=@Num_Cuenta and codigo_seguridad=@Codigo_Seguridad";

                SqlCommand cmd = new SqlCommand(sql, conn);

                //Defino parametros y sus caracteristicas
                SqlParameter Num_Cuentaparametro = new SqlParameter();
                Num_Cuentaparametro.SqlDbType = System.Data.SqlDbType.BigInt;
                Num_Cuentaparametro.ParameterName = "@Num_Cuenta";
                Num_Cuentaparametro.Value = Num_Cuenta;
                cmd.Parameters.Add(Num_Cuentaparametro);

                SqlParameter Codigo_Seguridadparametro = new SqlParameter();
                Codigo_Seguridadparametro.SqlDbType = System.Data.SqlDbType.BigInt;
                Codigo_Seguridadparametro.ParameterName = "@Codigo_Seguridad";
                Codigo_Seguridadparametro.Value = Codigo_Seguridad;
                cmd.Parameters.Add(Codigo_Seguridadparametro);

                // Llena los datos
                SqlDataAdapter da = new SqlDataAdapter(cmd);

                da.Fill(dt);

                if (dt.Rows.Count > 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }

            }
            catch (Exception ex)
            {
                return false;
            }
        }

        private Boolean contraseñaValida(int Num_Cuenta, string Contraseña) {
            System.Data.DataTable dt = new System.Data.DataTable();

            try
            {
                conn = new SqlConnection();
                // Hay que cambiar el Connection String de acuerdo a la PC
                conn.ConnectionString = sqlConnectionString;
                string sql = @"select * from [SW_Tarjetas].[dbo].[Compra_EasyPay] where num_cuenta=@Num_Cuenta and contraseña=@Contraseña";

                SqlCommand cmd = new SqlCommand(sql, conn);

                //Defino parametros y sus caracteristicas
                SqlParameter Num_Cuentaparametro = new SqlParameter();
                Num_Cuentaparametro.SqlDbType = System.Data.SqlDbType.BigInt;
                Num_Cuentaparametro.ParameterName = "@Num_Cuenta";
                Num_Cuentaparametro.Value = Num_Cuenta;
                cmd.Parameters.Add(Num_Cuentaparametro);

                SqlParameter Contraseñaparametro = new SqlParameter();
                Contraseñaparametro.SqlDbType = System.Data.SqlDbType.NVarChar;
                Contraseñaparametro.ParameterName = "@Contraseña";
                Contraseñaparametro.Value = Contraseña;
                cmd.Parameters.Add(Contraseñaparametro);

                // Llena los datos
                SqlDataAdapter da = new SqlDataAdapter(cmd);

                da.Fill(dt);

                if (dt.Rows.Count > 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }

            }
            catch (Exception ex)
            {
                return false;
            }
        }

        private Boolean fondosSuficientesEasyPay(int num_cuenta, Decimal Monto)
        {

            System.Data.DataTable dt = new System.Data.DataTable();

            try
            {
                conn = new SqlConnection();
                // Hay que cambiar el Connection String de acuerdo a la PC
                conn.ConnectionString = sqlConnectionString;
                string sql = @"select * from [SW_Tarjetas].[dbo].[Compra_EasyPay] where num_cuenta=@num_cuenta";

                SqlCommand cmd = new SqlCommand(sql, conn);

                //Defino parametros y sus caracteristicas
                SqlParameter Num_Tarjetaparametro = new SqlParameter();
                Num_Tarjetaparametro.SqlDbType = System.Data.SqlDbType.BigInt;
                Num_Tarjetaparametro.ParameterName = "@num_cuenta";
                Num_Tarjetaparametro.Value = num_cuenta;
                cmd.Parameters.Add(Num_Tarjetaparametro);

                // Llena los datos
                SqlDataAdapter da = new SqlDataAdapter(cmd);

                da.Fill(dt);

                if (dt.Rows.Count > 0)
                {
                    foreach (System.Data.DataRow item in dt.Rows)
                    {
                        decimal montoDB = Convert.ToDecimal(item.ItemArray[3].ToString());
                                               
                        decimal nuevoMonto = montoDB - Monto;

                            if (nuevoMonto >= 0)
                            {
                                actualizaSaldoEasyPay(num_cuenta, nuevoMonto);
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                        
                    }
                }
                else
                {
                    return false;
                }

                return false;

            }
            catch (Exception ex)
            {
                return false;
            }

        }

        [WebMethod]
        public string Test()
        {
            return "Resultado: "+actualizaSaldoEasyPay(123456, 100254m);
        }

            private Boolean actualizaSaldoEasyPay(int Num_Cuenta, Decimal NuevoMonto)
        {

            System.Data.DataTable dt = new System.Data.DataTable();

            try
            {
                conn = new SqlConnection();
                // Hay que cambiar el Connection String de acuerdo a la PC
                conn.ConnectionString = sqlConnectionString;
                string sql = @"update [SW_Tarjetas].[dbo].[Compra_EasyPay] SET fondo=@NuevoMonto where num_cuenta=@Num_Cuenta";

                SqlCommand cmd = new SqlCommand(sql, conn);

                //Defino parametros y sus caracteristicas
                SqlParameter num_cuentaparametro = new SqlParameter();
                num_cuentaparametro.SqlDbType = System.Data.SqlDbType.BigInt;
                num_cuentaparametro.ParameterName = "@Num_Cuenta";
                num_cuentaparametro.Value = Num_Cuenta;
                cmd.Parameters.Add(num_cuentaparametro);

                SqlParameter NuevoMontoparametro = new SqlParameter();
                NuevoMontoparametro.SqlDbType = System.Data.SqlDbType.BigInt;
                NuevoMontoparametro.ParameterName = "@NuevoMonto";
                NuevoMontoparametro.Value = NuevoMonto;
                cmd.Parameters.Add(NuevoMontoparametro);

                // Llena los datos
                SqlDataAdapter da = new SqlDataAdapter(cmd);

                da.Fill(dt);

                if (dt.Rows.Count > 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }

            }
            catch (Exception ex)
            {
                return false;
            }

        }

        private Boolean existeTarjeta(Int64 Num_Tarjeta) {

            System.Data.DataTable dt = new System.Data.DataTable();

            try
            {
                conn = new SqlConnection();
                // Hay que cambiar el Connection String de acuerdo a la PC
                conn.ConnectionString = sqlConnectionString;
                string sql = @"select * from [SW_Tarjetas].[dbo].[Compra_Tarjeta] where NUM_TARJETA=@NUMTARJETA";

                SqlCommand cmd = new SqlCommand(sql, conn);

                //Defino parametros y sus caracteristicas
                SqlParameter Num_Tarjetaparametro = new SqlParameter();
                Num_Tarjetaparametro.SqlDbType = System.Data.SqlDbType.BigInt;
                Num_Tarjetaparametro.ParameterName = "@NUMTARJETA";
                Num_Tarjetaparametro.Value = Num_Tarjeta;
                cmd.Parameters.Add(Num_Tarjetaparametro);
                
                // Llena los datos
                SqlDataAdapter da = new SqlDataAdapter(cmd);

                da.Fill(dt);

                if (dt.Rows.Count > 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }

            }
            catch (Exception ex)
            {
                return false;
            }

        }

        private Boolean fechaExpiracionInvalida(Int64 Num_Tarjeta, int Mes_Exp, int Año_Exp)
        {

            System.Data.DataTable dt = new System.Data.DataTable();

            try
            {
                conn = new SqlConnection();
                // Hay que cambiar el Connection String de acuerdo a la PC
                conn.ConnectionString = sqlConnectionString;
                string sql = @"select * from [SW_Tarjetas].[dbo].[Compra_Tarjeta] where NUM_TARJETA=@NUMTARJETA and MES_EXP=@MES_EXP and anno_exp=@anno_exp";

                SqlCommand cmd = new SqlCommand(sql, conn);

                //Defino parametros y sus caracteristicas
                SqlParameter Num_Tarjetaparametro = new SqlParameter();
                Num_Tarjetaparametro.SqlDbType = System.Data.SqlDbType.BigInt;
                Num_Tarjetaparametro.ParameterName = "@NUMTARJETA";
                Num_Tarjetaparametro.Value = Num_Tarjeta;
                cmd.Parameters.Add(Num_Tarjetaparametro);

                SqlParameter MES_EXPparametro = new SqlParameter();
                MES_EXPparametro.SqlDbType = System.Data.SqlDbType.BigInt;
                MES_EXPparametro.ParameterName = "@MES_EXP";
                MES_EXPparametro.Value = Mes_Exp;
                cmd.Parameters.Add(MES_EXPparametro);

                SqlParameter anno_expparametro = new SqlParameter();
                anno_expparametro.SqlDbType = System.Data.SqlDbType.BigInt;
                anno_expparametro.ParameterName = "@anno_exp";
                anno_expparametro.Value = Año_Exp;
                cmd.Parameters.Add(anno_expparametro);

                // Llena los datos
                SqlDataAdapter da = new SqlDataAdapter(cmd);

                da.Fill(dt);

                if (dt.Rows.Count > 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }

            }
            catch (Exception ex)
            {
                return false;
            }

        }

        private Boolean cvvValido(Int64 Num_Tarjeta, int CVV)
        {

            System.Data.DataTable dt = new System.Data.DataTable();

            try
            {
                conn = new SqlConnection();
                // Hay que cambiar el Connection String de acuerdo a la PC
                conn.ConnectionString = sqlConnectionString;
                string sql = @"select * from [SW_Tarjetas].[dbo].[Compra_Tarjeta] where NUM_TARJETA=@NUMTARJETA and cvv=@cvv";

                SqlCommand cmd = new SqlCommand(sql, conn);

                //Defino parametros y sus caracteristicas
                SqlParameter Num_Tarjetaparametro = new SqlParameter();
                Num_Tarjetaparametro.SqlDbType = System.Data.SqlDbType.BigInt;
                Num_Tarjetaparametro.ParameterName = "@NUMTARJETA";
                Num_Tarjetaparametro.Value = Num_Tarjeta;
                cmd.Parameters.Add(Num_Tarjetaparametro);

                SqlParameter cvvparametro = new SqlParameter();
                cvvparametro.SqlDbType = System.Data.SqlDbType.BigInt;
                cvvparametro.ParameterName = "@cvv";
                cvvparametro.Value = CVV;
                cmd.Parameters.Add(cvvparametro);

                // Llena los datos
                SqlDataAdapter da = new SqlDataAdapter(cmd);

                da.Fill(dt);

                if (dt.Rows.Count > 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }

            }
            catch (Exception ex)
            {
                return false;
            }

        }

        private Boolean fondosSuficientes(Int64 Num_Tarjeta, Decimal Monto)
        {

            System.Data.DataTable dt = new System.Data.DataTable();

            try
            {
                conn = new SqlConnection();
                // Hay que cambiar el Connection String de acuerdo a la PC
                conn.ConnectionString = sqlConnectionString;
                string sql = @"select * from [SW_Tarjetas].[dbo].[Compra_Tarjeta] where NUM_TARJETA=@NUMTARJETA";

                SqlCommand cmd = new SqlCommand(sql, conn);

                //Defino parametros y sus caracteristicas
                SqlParameter Num_Tarjetaparametro = new SqlParameter();
                Num_Tarjetaparametro.SqlDbType = System.Data.SqlDbType.BigInt;
                Num_Tarjetaparametro.ParameterName = "@NUMTARJETA";
                Num_Tarjetaparametro.Value = Num_Tarjeta;
                cmd.Parameters.Add(Num_Tarjetaparametro);

                // Llena los datos
                SqlDataAdapter da = new SqlDataAdapter(cmd);

                da.Fill(dt);

                if (dt.Rows.Count > 0)
                {
                    foreach (System.Data.DataRow item in dt.Rows)
                    {
                        decimal montoDB = Convert.ToDecimal(item.ItemArray[4].ToString());
                        String tipo = item.ItemArray[6].ToString();
                        int limite = Convert.ToInt32(item.ItemArray[7].ToString());

                        // Si es de tipo Debito
                        if (tipo.Equals("Debito"))
                        {
                            decimal nuevoMonto = montoDB - Monto;
                            if (nuevoMonto > 0)
                            {
                                actualizaSaldo(Num_Tarjeta, nuevoMonto);
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                        }// Si es de credito
                        else {
                            decimal nuevoMonto = montoDB + Monto;
                            if (limite > nuevoMonto) {
                                actualizaSaldo(Num_Tarjeta,nuevoMonto);
                                return true;
                            }
                            else
                            {
                                return false;
                            }
                        }
                    }
                }
                else
                {
                    return false;
                }

                return false;

            }
            catch (Exception ex)
            {
                return false;
            }

        }

        private Boolean actualizaSaldo(Int64 Num_Tarjeta, Decimal NuevoMonto) {

            System.Data.DataTable dt = new System.Data.DataTable();

            try
            {
                conn = new SqlConnection();
                // Hay que cambiar el Connection String de acuerdo a la PC
                conn.ConnectionString = sqlConnectionString;
                string sql = @"update [SW_Tarjetas].[dbo].[Compra_Tarjeta] SET monto=@NuevoMonto where NUM_TARJETA=@NUMTARJETA";

                SqlCommand cmd = new SqlCommand(sql, conn);

                //Defino parametros y sus caracteristicas
                SqlParameter Num_Tarjetaparametro = new SqlParameter();
                Num_Tarjetaparametro.SqlDbType = System.Data.SqlDbType.BigInt;
                Num_Tarjetaparametro.ParameterName = "@NUMTARJETA";
                Num_Tarjetaparametro.Value = Num_Tarjeta;
                cmd.Parameters.Add(Num_Tarjetaparametro);

                SqlParameter Montoaparametro = new SqlParameter();
                Montoaparametro.SqlDbType = System.Data.SqlDbType.Decimal;
                Montoaparametro.ParameterName = "@NuevoMonto";
                Montoaparametro.Value = NuevoMonto;
                cmd.Parameters.Add(Montoaparametro);

                // Llena los datos
                SqlDataAdapter da = new SqlDataAdapter(cmd);

                da.Fill(dt);

                if (dt.Rows.Count > 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }

            }
            catch (Exception ex)
            {
                return false;
            }

        }
    }
}