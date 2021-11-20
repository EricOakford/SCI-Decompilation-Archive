;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64962)
(include sci.sh)
(use Main)
(use Actpr)


(class Cursor of View
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
		view 0
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $4021
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
		hidden 0
	)
	
	(method (init)
		(SetCursor view loop cel)
		(return self)
	)
	
	(method (show)
		(if hidden (SetCursor 1) (= hidden 0))
	)
	
	(method (posn theMouseX theMouseY)
		(SetCursor theMouseX theMouseY)
		(= x (= mouseX theMouseX))
		(= y (= mouseY theMouseY))
	)
	
	(method (setLoop theLoop)
		(= loop theLoop)
		(self init:)
	)
	
	(method (setCel theCel)
		(= cel theCel)
		(self init:)
	)
	
	(method (hide)
		(if (not hidden) (SetCursor 0) (= hidden 1))
	)
	
	(method (setView theView)
		(= view theView)
		(self init:)
	)
)
