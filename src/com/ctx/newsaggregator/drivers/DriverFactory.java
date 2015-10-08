package com.ctx.newsaggregator.drivers;

import com.ctx.newsaggregator.Constants;
import com.ctx.newsaggregator.drivers.newsagencies.BrifDriver;
import com.ctx.newsaggregator.drivers.newsagencies.DWDriver;
import com.ctx.newsaggregator.drivers.newsagencies.EkonomskiDriver;
import com.ctx.newsaggregator.drivers.newsagencies.FaktorDriver;
import com.ctx.newsaggregator.drivers.newsagencies.InPressDriver;
import com.ctx.newsaggregator.drivers.newsagencies.Kanal77Driver;
import com.ctx.newsaggregator.drivers.newsagencies.KurirDriver;
import com.ctx.newsaggregator.drivers.newsagencies.LibertasDriver;
import com.ctx.newsaggregator.drivers.newsagencies.MKDMKDriver;
import com.ctx.newsaggregator.drivers.newsagencies.MKDNewsDriver;
import com.ctx.newsaggregator.drivers.newsagencies.MakFaxDriver;
import com.ctx.newsaggregator.drivers.newsagencies.MiaDriver;
import com.ctx.newsaggregator.drivers.newsagencies.NetPressDriver;
import com.ctx.newsaggregator.drivers.newsagencies.PlusInfoDriver;
import com.ctx.newsaggregator.drivers.newsagencies.Press24Driver;
import com.ctx.newsaggregator.drivers.newsagencies.Puls24Driver;
import com.ctx.newsaggregator.drivers.newsagencies.RepublikaDriver;
import com.ctx.newsaggregator.drivers.newsagencies.SkyDriver;
import com.ctx.newsaggregator.drivers.newspapers.DenesenDriver;
import com.ctx.newsaggregator.drivers.newspapers.DnevnikDriver;
import com.ctx.newsaggregator.drivers.newspapers.KapitalDriver;
import com.ctx.newsaggregator.drivers.newspapers.NovaMakedonijaDriver;
import com.ctx.newsaggregator.drivers.newspapers.UtrinskiVesnikDriver;
import com.ctx.newsaggregator.drivers.newspapers.VecerDriver;
import com.ctx.newsaggregator.drivers.newspapers.VestDriver;
import com.ctx.newsaggregator.drivers.newsportals.A1ONDriver;
import com.ctx.newsaggregator.drivers.newsportals.Aktuelno24Driver;
import com.ctx.newsaggregator.drivers.newsportals.BiznisInfoDriver;
import com.ctx.newsaggregator.drivers.newsportals.BiznisVestiDriver;
import com.ctx.newsaggregator.drivers.newsportals.BrkajRabotaDriver;
import com.ctx.newsaggregator.drivers.newsportals.CrnoBeloDriver;
import com.ctx.newsaggregator.drivers.newsportals.DenarDriver;
import com.ctx.newsaggregator.drivers.newsportals.DumaDriver;
import com.ctx.newsaggregator.drivers.newsportals.FokusDriver;
import com.ctx.newsaggregator.drivers.newsportals.HajdParkDriver;
import com.ctx.newsaggregator.drivers.newsportals.HitPortalDriver;
import com.ctx.newsaggregator.drivers.newsportals.IdiVidiDriver;
import com.ctx.newsaggregator.drivers.newsportals.InteresnoDriver;
import com.ctx.newsaggregator.drivers.newsportals.KafePauzeDriver;
import com.ctx.newsaggregator.drivers.newsportals.KajganaDriver;
import com.ctx.newsaggregator.drivers.newsportals.KukurikuDriver;
import com.ctx.newsaggregator.drivers.newsportals.KumanovoNewsDriver;
import com.ctx.newsaggregator.drivers.newsportals.MakModaDriver;
import com.ctx.newsaggregator.drivers.newsportals.MaximDriver;
import com.ctx.newsaggregator.drivers.newsportals.NaPauzaDriver;
import com.ctx.newsaggregator.drivers.newsportals.OffNetDriver;
import com.ctx.newsaggregator.drivers.newsportals.OhridNewsDriver;
import com.ctx.newsaggregator.drivers.newsportals.OhridPressDriver;
import com.ctx.newsaggregator.drivers.newsportals.OknoDriver;
import com.ctx.newsaggregator.drivers.newsportals.OnNetDriver;
import com.ctx.newsaggregator.drivers.newsportals.PoparaDriver;
import com.ctx.newsaggregator.drivers.newsportals.Portal365Driver;
import com.ctx.newsaggregator.drivers.newsportals.PortalDriver;
import com.ctx.newsaggregator.drivers.newsportals.SarmaMKDriver;
import com.ctx.newsaggregator.drivers.newsportals.SkopjeInfoDriver;
import com.ctx.newsaggregator.drivers.newsportals.TaraturDriver;
import com.ctx.newsaggregator.drivers.newsportals.TeaModernaDriver;
import com.ctx.newsaggregator.drivers.newsportals.TelegrafDriver;
import com.ctx.newsaggregator.drivers.newsportals.ThinkDriver;
import com.ctx.newsaggregator.drivers.newsportals.TockaDriver;
import com.ctx.newsaggregator.drivers.newsportals.UbavinaIZdravjeDriver;
import com.ctx.newsaggregator.drivers.newsportals.VistinaDriver;
import com.ctx.newsaggregator.drivers.newsportals.ZaSeDriver;
import com.ctx.newsaggregator.drivers.newsportals.ZaZabavaDriver;
import com.ctx.newsaggregator.drivers.tv.AlfaTVDriver;
import com.ctx.newsaggregator.drivers.tv.IrisDriver;
import com.ctx.newsaggregator.drivers.tv.Kanal5Driver;
import com.ctx.newsaggregator.drivers.tv.MTVDriver;
import com.ctx.newsaggregator.drivers.tv.NovaTVDriver;
import com.ctx.newsaggregator.drivers.tv.SitelDriver;
import com.ctx.newsaggregator.drivers.tv.TelmaDriver;
import com.ctx.newsaggregator.drivers.tv.Vesti24Driver;

public class DriverFactory {
	
	public static IDriver findDriverByName(String name) {
		IDriver mediumDriver = null;
		
		if (name.equals(Constants.MEDIUM_MK_DNEVNIK)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new DnevnikDriver();
		} else if (name.equals(Constants.MEDIUM_MK_SITEL)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new SitelDriver();
		} else if (name.equals(Constants.MEDIUM_MK_UTRINSKI_VESNIK)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new UtrinskiVesnikDriver();
		} else if (name.equals(Constants.MEDIUM_MK_VECER)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new VecerDriver();
		} else if (name.equals(Constants.MEDIUM_MK_VEST)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new VestDriver();
		} else if (name.equals(Constants.MEDIUM_MK_NOVA_MAKEDONIJA)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new NovaMakedonijaDriver();
		} else if (name.equals(Constants.MEDIUM_MK_KAPITAL)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new KapitalDriver();
		} else if (name.equals(Constants.MEDIUM_MK_TELMA)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new TelmaDriver();
		} else if (name.equals(Constants.MEDIUM_MK_KANAL5)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new Kanal5Driver();
		} else if (name.equals(Constants.MEDIUM_MK_ALFATV)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new AlfaTVDriver();
		} else if (name.equals(Constants.MEDIUM_MK_VESTI24)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new Vesti24Driver();
		} else if (name.equals(Constants.MEDIUM_MK_KURIR)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new KurirDriver();
		} else if (name.equals(Constants.MEDIUM_MK_NETPRESS)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new NetPressDriver();
		} else if (name.equals(Constants.MEDIUM_MK_REPUBLIKA)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new RepublikaDriver();
		} else if (name.equals(Constants.MEDIUM_MK_PLUSINFO)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new PlusInfoDriver();
		} else if (name.equals(Constants.MEDIUM_MK_MAKFAX)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new MakFaxDriver();
		} else if (name.equals(Constants.MEDIUM_MK_PRESS24)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new Press24Driver();
		} else if (name.equals(Constants.MEDIUM_MK_PULS24)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new Puls24Driver();
		} else if (name.equals(Constants.MEDIUM_MK_MKDMK)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new MKDMKDriver();
		} else if (name.equals(Constants.MEDIUM_MK_MKDNEWS)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new MKDNewsDriver();
		} else if (name.equals(Constants.MEDIUM_MK_BRIF)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new BrifDriver();
		} else if (name.equals(Constants.MEDIUM_MK_NOVATV)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new NovaTVDriver();
		} else if (name.equals(Constants.MEDIUM_MK_TELEGRAF)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new TelegrafDriver();
		} else if (name.equals(Constants.MEDIUM_MK_INPRESS)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new InPressDriver();
		} else if (name.equals(Constants.MEDIUM_MK_KANAL77)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new Kanal77Driver();
		} else if (name.equals(Constants.MEDIUM_MK_LIBERTAS)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new LibertasDriver();
		} else if (name.equals(Constants.MEDIUM_MK_SKY)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new SkyDriver();
		} else if (name.equals(Constants.MEDIUM_MK_ONNET)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new OnNetDriver();
		} else if (name.equals(Constants.MEDIUM_MK_OFFNET)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new OffNetDriver();
		} else if (name.equals(Constants.MEDIUM_MK_KAJGANA)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new KajganaDriver();
		} else if (name.equals(Constants.MEDIUM_MK_TOCKA)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new TockaDriver();
		} else if (name.equals(Constants.MEDIUM_MK_IDIVIDI)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new IdiVidiDriver();
		} else if (name.equals(Constants.MEDIUM_MK_ZAZABAVA)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new ZaZabavaDriver();
		} else if (name.equals(Constants.MEDIUM_MK_CRNOBELO)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new CrnoBeloDriver();
		} else if (name.equals(Constants.MEDIUM_MK_365)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new Portal365Driver();
		} else if (name.equals(Constants.MEDIUM_MK_KAFEPAUZA)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new KafePauzeDriver();
		} else if (name.equals(Constants.MEDIUM_MK_TARATUR)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new TaraturDriver();
		} else if (name.equals(Constants.MEDIUM_MK_POPARA)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new PoparaDriver();
		} else if (name.equals(Constants.MEDIUM_MK_BRKAJRABOTA)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new BrkajRabotaDriver();
		} else if (name.equals(Constants.MEDIUM_MK_INTERESNO)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new InteresnoDriver();
		} else if (name.equals(Constants.MEDIUM_MK_HITPORTAL)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new HitPortalDriver();
		} else if (name.equals(Constants.MEDIUM_MK_NAPAUZA)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new NaPauzaDriver();
		} else if (name.equals(Constants.MEDIUM_MK_HAJDPARK)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new HajdParkDriver();
		} else if (name.equals(Constants.MEDIUM_MK_THINKMK)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new ThinkDriver();
		} else if (name.equals(Constants.MEDIUM_MK_SARMAMK)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new SarmaMKDriver();
		} else if (name.equals(Constants.MEDIUM_MK_PORTAL)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new PortalDriver();
		} else if (name.equals(Constants.MEDIUM_MK_MIA)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new MiaDriver();
		} else if (name.equals(Constants.MEDIUM_MK_DW)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new DWDriver();
		} else if (name.equals(Constants.MEDIUM_MK_EKONOMSKI)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new EkonomskiDriver();
		} else if (name.equals(Constants.MEDIUM_MK_FAKTOR)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new FaktorDriver();
		} else if (name.equals(Constants.MEDIUM_MK_DENESEN)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new DenesenDriver();
		} else if (name.equals(Constants.MEDIUM_MK_KUKURIKU)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new KukurikuDriver();
		} else if (name.equals(Constants.MEDIUM_MK_A1ON)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new A1ONDriver();
		} else if (name.equals(Constants.MEDIUM_MK_MTV)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new MTVDriver();
		} else if (name.equals(Constants.MEDIUM_MK_BIZNISVESTI)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new BiznisVestiDriver();
		} else if (name.equals(Constants.MEDIUM_MK_MAXIM)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new MaximDriver();
		} else if (name.equals(Constants.MEDIUM_MK_DENAR)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new DenarDriver();
		} else if (name.equals(Constants.MEDIUM_MK_OKNO)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new OknoDriver();
		} else if (name.equals(Constants.MEDIUM_MK_ZASE)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new ZaSeDriver();
		} else if (name.equals(Constants.MEDIUM_MK_VISTINA)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new VistinaDriver();
		} else if (name.equals(Constants.MEDIUM_MK_BIZNISINFO)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new BiznisInfoDriver();
		} else if (name.equals(Constants.MEDIUM_MK_UBAVINAIZDRAVJE)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new UbavinaIZdravjeDriver();
		} else if (name.equals(Constants.MEDIUM_MK_MAKMODA)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new MakModaDriver();
		} else if (name.equals(Constants.MEDIUM_MK_FOKUS)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new FokusDriver();
		} else if (name.equals(Constants.MEDIUM_MK_SKOPJEINFO)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new SkopjeInfoDriver();
		} else if (name.equals(Constants.MEDIUM_MK_OHRIDNEWS)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new OhridNewsDriver();
		} else if (name.equals(Constants.MEDIUM_MK_OHRIDPRESS)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new OhridPressDriver();
		} else if (name.equals(Constants.MEDIUM_MK_KUMANOVONEWS)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new KumanovoNewsDriver();
		} else if (name.equals(Constants.MEDIUM_MK_IRIS)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new IrisDriver();
		} else if (name.equals(Constants.MEDIUM_MK_DUMA)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new DumaDriver();
		} else if (name.equals(Constants.MEDIUM_MK_TEAMODERNA)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new TeaModernaDriver();
		} else if (name.equals(Constants.MEDIUM_MK_AKTUELNO24)) {
			System.out.println("Medium Driver Selected: " + name);
			mediumDriver = new Aktuelno24Driver();
		} 
		
		return mediumDriver;
	}

}
