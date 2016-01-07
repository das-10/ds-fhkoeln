package de.thkoeln.pattern.factory;

import de.thkoeln.pattern.entities.CDVoucher;
import de.thkoeln.pattern.entities.CommercialGift;
import de.thkoeln.pattern.entities.FatherChristmas;
import de.thkoeln.pattern.entities.Voucher;

public class ChristmasGift extends Gift {

	@Override
	public Voucher createVoucher() {
		// TODO Auto-generated method stub
		return new CDVoucher(30);
	}

	@Override
	public CommercialGift createCommercialGift() {
		return new FatherChristmas();
	}

}
