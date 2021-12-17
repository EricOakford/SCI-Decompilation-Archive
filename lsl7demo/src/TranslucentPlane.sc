;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64884)
(include sci.sh)
(use Plane)
(use Actor)
(use System)


(class TranslucentPlane of Plane
	(properties
		scratch 0
		resX -1
		resY -1
		left 0
		top 0
		right 0
		bottom 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		vanishingX 0
		vanishingY 0
		coordType 0
		picture -2
		style $0000
		priority -1
		back 0
		bitmap 0
		casts 0
		mirrored 0
		oMyFeatures 0
		nBorderWidth 2
		remapColor 236
		remapPercent 45
	)
	
	(method (init &tmp translucentPlaneGetWidth translucentPlaneGetHeight temp2 temp3 temp4)
		(super init: &rest)
		(self addCast: (Cast new:))
		(RemapColors 2 remapColor remapPercent)
		(= translucentPlaneGetWidth (self getWidth:))
		(= translucentPlaneGetHeight (self getHeight:))
		(= temp2 (* nBorderWidth 32))
		(= temp4 (* translucentPlaneGetWidth 32))
		(= temp3 (* translucentPlaneGetHeight 32))
		((TransView new:)
			remapColor: remapColor
			x: 0
			y: 0
			setPri: 0
			scaleSignal: 1
			scaleX: temp4
			scaleY: temp3
			maxScale: 20480
			init: (casts at: 0)
		)
		(if nBorderWidth
			((TransView new:)
				remapColor: remapColor
				x: 0
				y: 0
				setPri: 1
				scaleSignal: 1
				scaleX: temp4
				scaleY: temp2
				maxScale: 20480
				init: (casts at: 0)
			)
			((TransView new:)
				remapColor: remapColor
				x: 0
				y: nBorderWidth
				setPri: 1
				scaleSignal: 1
				scaleX: temp2
				scaleY: (* (- translucentPlaneGetHeight nBorderWidth) 32)
				maxScale: 20480
				init: (casts at: 0)
			)
			((TransView new:)
				remapColor: remapColor
				x: (- translucentPlaneGetWidth nBorderWidth)
				y: nBorderWidth
				setPri: 1
				scaleSignal: 1
				scaleX: temp2
				scaleY: (* (- translucentPlaneGetHeight nBorderWidth) 32)
				maxScale: 20480
				init: (casts at: 0)
			)
			((TransView new:)
				remapColor: remapColor
				x: nBorderWidth
				y: (- translucentPlaneGetHeight nBorderWidth)
				setPri: 1
				scaleSignal: 1
				scaleX:
					(*
						(-
							translucentPlaneGetWidth
							(+ nBorderWidth nBorderWidth)
						)
						32
					)
				scaleY: temp2
				maxScale: 20480
				init: (casts at: 0)
			)
		)
	)
	
	(method (setPri)
		(RemapColors 2 remapColor remapPercent)
		(super setPri: &rest)
	)
	
	(method (changeRemap theRemapPercent)
		(= remapPercent theRemapPercent)
		(RemapColors 2 remapColor remapPercent)
	)
)

(class TransView of View
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
		remapColor 236
	)
	
	(method (init)
		(= bitmap (Bitmap 0 4 4 255 remapColor 640 480 1))
		(Bitmap 5 bitmap 0 0 3 3 remapColor)
		(super init: &rest)
	)
	
	(method (dispose &tmp theBitmap)
		(= theBitmap 0)
		(if bitmap (= theBitmap bitmap) (= bitmap 0))
		(super dispose:)
		(if theBitmap (Bitmap 1 theBitmap))
	)
)
