;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64911)
(include sci.sh)
(use Intrface)
(use DText)
(use System)


(class DButton of DText
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
		state $0003
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
		type $0002
		key 0
		value 0
		object 0
		selector 0
		textLeft 0
		textTop 0
		textRight 0
		textBottom 0
		text 0
		mode 1
		fore 255
		back 0
		skip 254
		font 0
		borderColor 0
		dimmed 0
		rects 0
	)
	
	(method (init)
		(if (!= view -1) (= borderColor -1) (= back skip))
		(super init: &rest)
	)
	
	(method (hilite param1)
		(if (and argc param1)
			(= state (| state $0008))
			(cond 
				((!= view -1) (= cel 1))
				((!= borderColor -1) (= borderColor fore))
			)
		else
			(= state (& state $fff7))
			(cond 
				((!= view -1) (= cel 0))
				((!= borderColor -1) (= borderColor 0))
			)
		)
		(self draw:)
		(UpdateScreenItem self)
	)
	
	(method (track param1 &tmp temp0 temp1)
		(return
			(if (== 1 (param1 type?))
				(= temp1 0)
				(repeat
					((= param1 (Event new: -32768)) localize: plane)
					(if (!= (= temp0 (self onMe: param1)) temp1)
						(if temp0
							(= cel 2)
							(++ textTop)
							(++ textBottom)
						else
							(= cel 0)
							(-- textTop)
							(-- textBottom)
						)
						(self draw:)
						(UpdateScreenItem self)
						(FrameOut)
						(= temp1 temp0)
					)
					(param1 dispose:)
					(breakif (not (GetNumber)))
				)
				(if temp0 (-- textTop) (-- textBottom))
				(self hilite: (& state $0008))
				(return (if temp0 self else 0))
			else
				(return self)
			)
		)
	)
	
	(method (setSize &tmp [temp0 6])
		(super setSize: &rest)
	)
	
	(method (draw)
		(= dimmed (not (& state $0001)))
		(super draw:)
	)
)
