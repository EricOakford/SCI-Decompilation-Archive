;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64944)
(include sci.sh)
(use DSelector)
(use String)


(class FileSelector of DSelector
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
		x 13
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
		type $0000
		key 0
		value 0
		object 0
		selector 0
		font 0
		length 0
		width 100
		textHeight 0
		first 0
		current 0
		listPlane 0
		textList 0
		fore 0
		back 255
		slider 0
		knob 0
		upButton 0
		downButton 0
		mask 0
		nFiles 0
		sort 1
	)
	
	(method (init)
		(self readFiles:)
		(super init: &rest)
	)
	
	(method (readFiles &tmp temp0 temp1)
		(= temp0 (Str new: 13))
		(= nFiles 0)
		(= temp1 (FileIO fiFIND_FIRST mask (temp0 data?) 0))
		(while temp1
			(self setText: (temp0 data?))
			(++ nFiles)
			(= temp1 (FileIO fiFIND_NEXT (temp0 data?)))
		)
		(if sort (self sortFileNames:))
		(temp0 dispose:)
		(return 1)
	)
	
	(method (sortFileNames)
	)
)
