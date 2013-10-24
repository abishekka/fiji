package fiji.plugin.interestpoints;

import ij.gui.GenericDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mpicbg.models.Point;
import mpicbg.spim.data.sequence.Channel;
import mpicbg.spim.data.sequence.ViewId;
import fiji.spimdata.SpimDataBeads;


public class DifferenceOfMean extends DifferenceOf
{
	public static int defaultRadius1[];
	public static int defaultRadius2[];
	public static double defaultThreshold[];
	public static boolean defaultFindMin[];
	public static boolean defaultFindMax[];
	
	int[] smallRadius;
	int[] largeRadius;
	double[] threshold;
	boolean[] findMin;
	boolean[] findMax;
	
	@Override
	public String getDescription() { return "Difference-of-Mean (Integral image based)"; }

	@Override
	public DifferenceOfMean newInstance() { return new DifferenceOfMean(); }

	@Override
	public HashMap< ViewId, List<Point> > findInterestPoints( final SpimDataBeads spimData, final boolean[] channelIds, final ArrayList<Integer> timepointindices )
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected boolean setDefaultValues( final Channel channel, final int brightness )
	{
		final int channelId = channel.getId();
		
		this.smallRadius[ channelId ] = 2;
		this.largeRadius[ channelId ] = 3;
		this.findMin[ channelId ] = false;
		this.findMax[ channelId ] = true;
		
		if ( brightness == 0 )
			this.threshold[ channelId ] = 0.0025f;
		else if ( brightness == 1 )
			this.threshold[ channelId ] = 0.02f;
		else if ( brightness == 2 )
			this.threshold[ channelId ] = 0.075f;
		else if ( brightness == 3 )
			this.threshold[ channelId ] = 0.25f;
		else
			return false;
		
		return true;
	}

	@Override
	protected boolean setAdvancedValues( final Channel channel )
	{
		final int channelId = channel.getId();
		
		final GenericDialog gd = new GenericDialog( "Advanced values for channel " + channel.getName() );
		
		gd.addNumericField( "Radius_1", defaultRadius1[ channelId ], 0 );
		gd.addNumericField( "Radius_2", defaultRadius2[ channelId ], 0 );
		gd.addNumericField( "Threshold", defaultThreshold[ channelId ], 4 );
		gd.addCheckbox( "Find_minima", defaultFindMin[ channelId ] );
		gd.addCheckbox( "Find_maxima", defaultFindMax[ channelId ] );

		gd.showDialog();
		
		if ( gd.wasCanceled() )
			return false;
		
		this.smallRadius[ channelId ] = defaultRadius1[ channelId ] = (int)Math.round( gd.getNextNumber() );
		this.largeRadius[ channelId ] = defaultRadius2[ channelId ] = (int)Math.round( gd.getNextNumber() );
		this.threshold[ channelId ] = defaultThreshold[ channelId ] = gd.getNextNumber();
		this.findMin[ channelId ] = defaultFindMin[ channelId ] = gd.getNextBoolean();
		this.findMax[ channelId ] = defaultFindMax[ channelId ] = gd.getNextBoolean();
		
		return true;
	}

	@Override
	protected boolean setInteractiveValues( final Channel channel )
	{
		
		// TODO Auto-generated method stub		
		return false;
	}
	

	@Override
	protected void init( final int numChannels )
	{
		smallRadius = new int[ numChannels ];
		largeRadius = new int[ numChannels ];
		threshold = new double[ numChannels ];
		findMin = new boolean[ numChannels ];
		findMax = new boolean[ numChannels ];

		if ( defaultRadius1 == null || defaultRadius1.length != numChannels )
		{
			defaultRadius1 = new int[ numChannels ];
			defaultRadius2 = new int[ numChannels ];
			defaultThreshold = new double[ numChannels ];
			defaultFindMin = new boolean[ numChannels ];
			defaultFindMax = new boolean[ numChannels ];
			
			for ( int c = 0; c < numChannels; ++c )
			{
				defaultRadius1[ c ] = 2;
				defaultRadius2[ c ] = 3;
				defaultThreshold[ c ] = 0.02;
				defaultFindMin[ c ] = false;
				defaultFindMax[ c ] = true;
			}
		}
	}
}
