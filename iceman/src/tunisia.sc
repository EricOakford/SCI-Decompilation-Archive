;;; Sierra Script 1.0 - (do not remove this comment)
(script# rgTunisia)
(include game.sh)
(use Main)
(use Intrface)
(use GoToSaid)
(use Game)
(use Actor)
(use System)

(public
	tunisia 0
	InitArab 1
	DisposeArab 2
	agent 3
	bagdad 4
	plate 5
	eClothes 6
	bClothes 7
)

(procedure (InitArab param1 &tmp temp0)
	(arabicBubbleView init:)
	(switch (param1 loop?)
		(1
			(if (< (= temp0 (- (param1 x?) 10)) 48) (= temp0 48))
			(if (> temp0 285) (= temp0 285))
			(arabicBubbleView
				posn: temp0 (param1 y?) 41
				show:
				cel: 0
			)
		)
		(0
			(if (< (= temp0 (+ (param1 x?) 12)) 18) (= temp0 18))
			(if (> temp0 271) (= temp0 271))
			(arabicBubbleView
				posn: temp0 (param1 y?) 41
				show:
				cel: 1
			)
		)
		(3
			(if (< (= temp0 (- (param1 x?) 10)) 48) (= temp0 48))
			(if (> temp0 285) (= temp0 285))
			(arabicBubbleView
				posn: temp0 (param1 y?) 41
				show:
				cel: 0
			)
		)
		(else 
			(if (< (= temp0 (+ (param1 x?) 12)) 18) (= temp0 18))
			(if (> temp0 271) (= temp0 271))
			(arabicBubbleView
				posn: temp0 (param1 y?) 41
				show:
				cel: 1
			)
		)
	)
)

(procedure (DisposeArab)
	(arabicBubbleView dispose:)
)

(class tunisia of Region
	(properties
		milkIs 0
		cheezIs 0
		fridgeIs 0
		vanWas 0
		removedHook 0
		sugarStat 0
		flourStat 0
		coffeeStat 0
		bagBound 0
		madeCall 0
		flags $0000
		bagTimer 0
		pointFlag 0
	)
	
	(method (init)
		(super init: &rest)
		(if (== prevRoomNum 45)
			(self fridgeIs: FALSE)
			(self milkIs: TRUE)
			(self cheezIs: TRUE)
			(self vanWas: FALSE)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at]>')
				(cond 
					((Said '/line[<fishing]')
						(cond 
							((not (ego has: iFish))
								(event claimed: FALSE)
							)
							(removedHook
								(Print 310 0)
							)
							(else
								(Print 310 1)
							)
						)
					)
					((Said '/hook')
						(cond 
							((not (ego has: iFish))
								(event claimed: FALSE)
							)
							(removedHook
								(Print 310 2)
							)
							(else
								(Print 310 3)
							)
						)
					)
					((Said '/weight')
						(cond 
							((not (ego has: iFish))
								(event claimed: FALSE)
							)
							(removedHook
								(Print 310 4)
								(ego get: iCapsule)
							)
							(else
								(Print 310 5)
							)
						)
					)
					((Said '/road')
						(Print 310 6)
					)
				)
			)
			((Said 'detach/weight')
				(cond 
					((not (ego has: iFish))
						(event claimed: FALSE)
					)
					(removedHook
						(Print 310 4)
						(ego get: iCapsule)
					)
					(else
						(Print 310 5)
					)
				)
			)
			((Said 'look<in/mouth')
				(cond 
					((not (ego has: iFish))
						(event claimed: FALSE)
					)
					(removedHook
						(Print 310 7)
					)
					(else
						(Print 310 8)
					)
				)
			)
			((Said 'detach,get/hook,(line[<fishing])')
				(cond 
					((not (ego has: iFish))
						(event claimed: FALSE)
					)
					(removedHook
						(Print 310 9)
					)
					(else
						(= removedHook TRUE)
						(Print 310 10)
						(Print 310 11)
						(theGame changeScore: 1)
					)
				)
			)
			((Said 'open/capsule')
				(cond 
					((not (ego has: iCapsule))
						(event claimed: FALSE)
					)
					((and (ego has: iMap) (not ((inventory at: iMap) loop?)))
						(Print 310 9)
					)
					(else
						(Print 310 12)
						(ego get: iMap)
						(theGame changeScore: 1)
					)
				)
			)
			((Said 'look/sidewalk')
				(if (OneOf curRoomNum 77 78 79 80 81 82 83)
					(Print 310 13)
				else
					(event claimed: FALSE)
				)
			)
			((Said 'get,push,climb,look[<below]/rock')
				(Print 310 14)
			)
			((Said 'pull/line[<fishing]')
				(cond 
					((not (ego has: iFish))
						(event claimed: FALSE)
					)
					(removedHook
						(Print 310 15)
					)
					(else
						(Print 310 16)
					)
				)
			)
			((Said 'conceal')
				(Print 310 17)
			)
			((Said 'pole')
				(Print 310 18)
			)
			((Said 'undress')
				(Print 310 19)
			)
			((Said 'detach,wear,change/coat,clothes,(gear<scuba)')
				(Print 310 20)
			)
			((Said 'swallow/capsule')
				(Print 310 21)
			)
			(
				(Said
					'examine,look[<at]/gear,scuba,coat,wetsuit,equipment'
				)
				(Print 310 22)
			)
		)
	)
	
	(method (newRoom roomNum)
		(= keep
			(OneOf roomNum
				70 71 72 73 74 75 76 77 78
				79 80 81 82 83 84 85 86
			)
		)
		(= initialized FALSE)
		(super newRoom: &rest)
	)
)

(instance arabicBubbleView of View
	(properties
		view 270
		cel 1
	)
	
	(method (init)
		(super init:)
		(self hide:)
	)
)

(instance agent of Actor
	(properties
		y 200
		x 126
		view 250
		loop 3
	)
)

(instance bagdad of Actor
	(properties
		y 200
		x 126
		view 184
		loop 3
	)
)

(instance plate of View
	(properties
		y 118
		x 224
		z 28
		view 584
		loop 1
		cel 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((TurnIfSaid self event 'look[<at]/platter,food'))
			((Said 'look[<at][/platter,food]')
				(Print 310 23)
			)
			((Said 'get,(pick<up)/platter,food')
				(DontNeedTo)
			)
			((Said 'eat/food')
				(Print 310 24)
			)
			((Said 'detach/lid,cover,top[<tray]')
				(DontNeedTo)
			)
		)
	)
)

(instance eClothes of View
	(properties
		view 584
		loop 1
	)
)

(instance bClothes of View
	(properties
		view 584
		loop 1
		cel 1
	)
)
