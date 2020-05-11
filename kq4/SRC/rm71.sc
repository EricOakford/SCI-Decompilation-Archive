;;; Sierra Script 1.0 - (do not remove this comment)
(script# 71)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room71 0
)

(local
	caveForeground
	bone
	bonePile
	oldEgoView
)
(procedure (ShowCaveForeground visible)
	(if visible
		((= caveForeground (View new:))
			view: 800
			posn: 108 132
			init:
			setPri: 15
			stopUpd:
		)
	else
		(caveForeground dispose:)
		(= caveForeground FALSE)
	)
)

(instance Room71 of Room
	(properties
		picture 71
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load VIEW 800)
		(Load VIEW 511)
		(Load VIEW 940)
		(super init:)
		(= south 74)
		(self setRegions: TROLL_CAVE)
		(= isIndoors TRUE)
		((= bonePile (Prop new:))
			view: 511
			loop: 0
			cel: 0
			posn: 72 121
			stopUpd:
			init:
		)
		(if (== ((inventory at: iBone) owner?) 71)
			((= bone (Prop new:))
				view: 511
				ignoreActors: 1
				loop: 0
				cel: 1
				posn: 72 116
				stopUpd:
				init:
			)
		)
		(ego view: 904 xStep: 4 yStep: 1)
		(switch prevRoomNum
			(72 (ego posn: 208 104))
			(74 (ego posn: 170 183))
			(else 
				(ego posn: 78 112)
				(= trollAttacks FALSE)
				(if (not (LanternIsOn)) (ShowCaveForeground 1))
			)
		)
		(ego priority: (CoordPri (ego y?)) init:)
		(if (LanternIsOn) (NotifyScript 605 1))
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((& (ego onControl: 0) $0040) (curRoom newRoom: 70))
			((& (ego onControl: 0) $0020) (curRoom newRoom: 72))
		)
		(cond 
			((and caveForeground (LanternIsOn)) (ShowCaveForeground 0))
			((and (not caveForeground) (not (LanternIsOn))) (ShowCaveForeground 1))
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look>')
						(cond 
							((or (Said '/falls') (Said '<out')) (Print 71 0))
							((Said '/<') (Print 71 1))
							(
								(and
									(not (ego has: iBone))
									(or (Said '/[') (Said '/dirt') (Said '<down'))
								)
								(Print 71 1)
							)
							((or (Said '<around') (Said '/room,cave'))
								(Print
									(Format @str 71 2
										(if (LanternIsOn)
											{Even the lantern does little good.}
										else
											{_}
										)
									)
								)
								(if (not (ego has: iBone)) (Print 71 3))
							)
						)
					)
					((Said 'get/[')
						(cond 
							((!= ((inventory at: iBone) owner?) 71) (Print 71 4))
							(
								(and
									(< (ego distanceTo: bone) 15)
									(== ((inventory at: iBone) owner?) 71)
								)
								(ego get: iBone)
								(ego setScript: getDown)
								(bone hide:)
								(= gotItem TRUE)
								(theGame changeScore: 2)
							)
							(else (NotClose))
						)
					)
				)
			else
				0
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (== newRoomNumber 70)
			((ScriptID TROLL_CAVE) keep: 0)
			(= noWearCrown 0)
		)
		(super newRoom: newRoomNumber)
	)
)

(instance getDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= oldEgoView (ego view?))
				(ego
					setMotion: 0
					view: 940
					cel: 255
					loop: (if (< (ego heading?) 180) 0 else 1)
					setCycle: EndLoop self
				)
			)
			(1 (ego setCycle: BegLoop self))
			(2
				(ego view: oldEgoView setCycle: Walk)
				(HandsOn)
			)
		)
	)
)
