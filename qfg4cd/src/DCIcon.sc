;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64967)
(include sci.sh)
(use DIcon)
(use Motion)


(class DCIcon of DIcon
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
		type $0004
		key 0
		value 0
		object 0
		selector 0
		cycler 0
		cycleSpeed 6
	)
	
	(method (init)
		((= cycler (Fwd new:)) init: self)
	)
	
	(method (dispose)
		(if cycler (cycler dispose:))
		(super dispose:)
	)
	
	(method (lastCel)
		(return (- (NumCels self) 1))
	)
	
	(method (cycle &tmp theCel)
		(if cycler
			(= theCel cel)
			(cycler doit:)
			(if (!= cel theCel) (self draw:))
		)
	)
)
