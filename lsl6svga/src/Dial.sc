;;; Sierra Script 1.0 - (do not remove this comment)
(script# 72)
(include sci.sh)
(use Main)
(use IconBar)
(use System)


(class Dial of IconI
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
		signal $0001
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
		type $4000
		message -1
		modifiers $0000
		mainView 0
		mainLoop 0
		mainCel 0
		maskView 0
		maskLoop 0
		maskCel 0
		cursorView -1
		cursorLoop -1
		cursorCel -1
		highlightColor 0
		lowlightColor 0
		helpVerb 0
		object 0
		selector 0
		points 0
		locations 0
		curPos 0
	)
	
	(method (init param1)
		(= curPos (= mainCel (self update:)))
		(super init: (if (not argc) gameControls else param1))
	)
	
	(method (select &tmp newEvent temp1)
		(while (!= ((= newEvent (Event new:)) type?) 2)
			(newEvent localize: plane)
			(cond 
				(
				(> curPos (= temp1 (self findClosestPoint: newEvent))) (self decrement:))
				((< curPos temp1) (self increment:))
			)
			(newEvent dispose:)
		)
		(newEvent dispose:)
		(return curPos)
	)
	
	(method (increment)
		(self update: (= cel (++ curPos)))
		(UpdateScreenItem self)
		(FrameOut)
	)
	
	(method (decrement)
		(self update: (= cel (-- curPos)))
		(UpdateScreenItem self)
		(FrameOut)
	)
	
	(method (findClosestPoint param1 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6)
		(= temp0 0)
		(while (< temp0 points)
			(= temp3 (* temp0 2))
			(= temp1 (+ x (locations at: temp3)))
			(= temp2 (+ y (locations at: (+ temp3 1))))
			(cond 
				((== temp0 0)
					(= temp4
						(GetDistance (param1 x?) (param1 y?) temp1 temp2)
					)
					(= temp5 0)
				)
				(
					(<
						(= temp6
							(GetDistance (param1 x?) (param1 y?) temp1 temp2)
						)
						temp4
					)
					(= temp4 temp6)
					(= temp5 temp0)
				)
			)
			(++ temp0)
		)
		(return temp5)
	)
	
	(method (update)
	)
)
