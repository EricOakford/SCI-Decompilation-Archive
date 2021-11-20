;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64914)
(include sci.sh)
(use DText)
(use Array)


(class DEdit of DText
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
		state $0001
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
		signal $0000
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
		type $0003
		key 0
		value 0
		object 0
		selector 0
		textLeft 0
		textTop 0
		textRight 0
		textBottom 0
		text 0
		mode 0
		fore 0
		back 254
		skip 254
		font 1
		borderColor 0
		dimmed 0
		rects 0
		width 0
		title 0
		titleFore 0
		titleBack 0
		titleFont 0
		frameOut 1
		lastKey 0
	)
	
	(method (dispose)
		(super dispose: 1)
	)
	
	(method (hilite param1 &tmp theBitmap)
		(if (and argc param1)
			(= theBitmap bitmap)
			(= bitmap 0)
			(DeleteScreenItem self)
			(if (EditText self)
				(Bitmap 1 theBitmap)
				(self draw:)
			else
				(= bitmap theBitmap)
			)
			(AddScreenItem self)
		)
	)
	
	(method (setSize &tmp temp0 theTextLeft temp2)
		(= theTextLeft (if (!= borderColor -1) 2 else 0))
		(= nsTop (= nsLeft 0))
		(= textTop (= textLeft theTextLeft))
		(if (== view -1)
			(= temp0 (IntArray with: 0 0 0 0))
			(TextSize (temp0 data?) {M} font 0)
			(= nsRight
				(+
					nsLeft
					(= temp2 (* (+ (temp0 at: 2) 1) width))
					(* theTextLeft 2)
				)
			)
			(= nsBottom
				(+ nsTop (temp0 at: 3) (- (* theTextLeft 2) 1))
			)
			(= textRight (+ textLeft temp2))
			(= textBottom (+ textTop (temp0 at: 3)))
			(temp0 dispose:)
		else
			(= nsRight (+ nsLeft (CelWide view loop cel)))
			(= nsBottom (+ nsTop (CelHigh view loop cel)))
			(= textRight (- nsRight theTextLeft))
			(= textBottom (- nsBottom theTextLeft))
		)
	)
)
