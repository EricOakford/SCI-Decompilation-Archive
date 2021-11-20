;;; Sierra Script 1.0 - (do not remove this comment)
(script# 8)
(include sci.sh)
(use Main)
(use Feature)
(use Actor)
(use System)


(class TargFeature of Feature
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
		signal $1000
	)
	
	(method (init)
		(super init:)
		(if (not global392)
			(= global392 ((List new:) add: yourself:))
		)
		(if (not (global392 contains: self))
			(global392 add: self)
		)
	)
	
	(method (dispose)
		(global392 delete: self)
		(if (and global392 (not (global392 size:)))
			(global392 dispose:)
			(= global392 0)
		)
		(super dispose:)
	)
	
	(method (getHurt)
	)
)

(class TargProp of Prop
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
		signal $1000
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
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
	)
	
	(method (init)
		(super init:)
		(if (not global392)
			(= global392 ((List new:) add: yourself:))
		)
		(if (not (global392 contains: self))
			(global392 add: self)
		)
	)
	
	(method (dispose)
		(global392 delete: self)
		(if (and global392 (not (global392 size:)))
			(global392 dispose:)
			(= global392 0)
		)
		(super dispose:)
	)
	
	(method (getHurt)
	)
)

(class TargActor of Actor
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
		signal $1000
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
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		origStep 770
		moveSpeed 6
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
		robot 0
	)
	
	(method (init)
		(super init:)
		(if (not global392)
			(= global392 ((List new:) add: yourself:))
		)
		(if (not (global392 contains: self))
			(global392 add: self)
		)
	)
	
	(method (dispose)
		(global392 delete: self)
		(if (and global392 (not (global392 size:)))
			(global392 dispose:)
			(= global392 0)
		)
		(super dispose:)
	)
	
	(method (getHurt)
	)
)
