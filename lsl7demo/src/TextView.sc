;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64890)
(include sci.sh)
(use Main)
(use String)
(use Actor)


(class TextView of View
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $5021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		text 0
		font 0
		just 0
		fore 1
		back 0
		skip 2
		width 300
		height 20
		margin 2
	)
	
	(method (init)
		(= bitmap
			(Bitmap 0 width height skip back screenWidth screenHeight)
		)
		(Bitmap 5 bitmap 0 0 (- width 1) (- height 1) back)
		(super init: &rest)
	)
	
	(method (dispose)
		(if text (text dispose:) (= text 0))
		(super dispose: &rest)
	)
	
	(method (draw)
		(if argc
			(if (not text) (= text (Str new:)))
			(text format: &rest)
		)
		(Bitmap 5 bitmap 0 0 (- width 1) (- height 1) back)
		(Bitmap
			4
			bitmap
			(text data?)
			margin
			margin
			(- width (+ margin 1))
			(- height (+ margin 1))
			fore
			back
			skip
			font
			just
			back
			0
		)
		(UpdateScreenItem self)
	)
)
