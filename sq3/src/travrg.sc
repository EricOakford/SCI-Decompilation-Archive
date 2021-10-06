;;; Sierra Script 1.0 - (do not remove this comment)
(script# TRAVEL)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	travrg 0
)

(instance travrg of Region
	(method (init)
		(Load VIEW 51)
		(Load SOUND 33)
		(self keep: 0)
		(if global594
			(= global175 6)
		)
		(super init:)
	)
	
	(method (doit &tmp [temp0 50])
		(if global219
			(-- global220)
			(-- global175)
		)
		(if (== global175 15)
			(Print 701 0)
		)
		(if (and (== global175 1) (!= curRoomNum 18))
			(= global175 0)
			(if (== curRoomNum 14)
				(self setScript: Attack1)
			else
				(self setScript: Attack2)
			)
		)
		(if (and (== global220 1) global209 (not twoGuysOnBoard))
			(cond 
				((== global209 2) (= global220 180))
				((== global209 3) (= global220 90))
				(else (= global220 4))
			)
			(= global161 (+ global161 global165))
			(= gGEgoY_5 (+ gGEgoY_5 global166))
			(if
			(and (== global161 global163) (== gGEgoY_5 global164))
				(= global220 0)
				(= global167 1)
			else
				(if (== global161 12)
					(= currentSector (* global161 gGEgoY_5))
				else
					(= currentSector (+ (* (- gGEgoY_5 1) 12) global161))
				)
				(if (> global163 global161)
					(= global165 1)
				)
				(if (< global163 global161)
					(= global165 -1)
				)
				(if (== global163 global161)
					(= global165 0)
				)
				(if (> global164 gGEgoY_5)
					(= global166 1)
				)
				(if (< global164 gGEgoY_5)
					(= global166 -1)
				)
				(if (== global164 gGEgoY_5)
					(= global166 0)
				)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (or (event claimed?) (!= (event type?) saidEvent))
			(return)
		)
	)
)

(instance Attack1 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== global594 2)
					(self changeState: 4)
				else
					(ShakeScreen 5 shakeSDiagonal)
					(= global594 1)
					(if
						(or
							(not (-- shipShieldHealth))
							(== shieldActivated 1)
							(== shieldActivated 0)
						)
						(client setScript: BlowUp)
					)
					(zipShip
						posn: 128 69
						setLoop: 2
						cel: 0
						setPri: 6
						illegalBits: 0
						init:
						cycleSpeed: 0
						setCycle: EndLoop self
					)
				)
			)
			(1
				(zipShip cel: 0 setCycle: EndLoop self)
			)
			(2
				(zipShip cel: 0 setCycle: EndLoop self)
			)
			(3
				(zipShip
					cel: 0
					setLoop: 3
					posn: 142 79
					setCycle: EndLoop self
				)
			)
			(4
				(= global594 2)
				(= seconds 4)
			)
			(5
				(if (or (== global594 2) (not (cast contains: zipShip)))
					(zipShip init:)
				)
				(zipShip
					cel: 0
					setLoop: 0
					posn: 185 72
					cycleSpeed: 0
					setCycle: EndLoop self
				)
			)
			(6
				(zipShip
					cel: 0
					setLoop: 1
					posn: 195 80
					setCycle: EndLoop self
				)
			)
			(7
				(zipShip cel: 0 setCycle: EndLoop self)
			)
			(8
				(zipShip setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(9
				(zipShip dispose:)
				(RedrawCast)
				(ShakeScreen 5 shakeSDiagonal)
				(if
					(or
						(not (-- shipShieldHealth))
						(== shieldActivated 2)
						(== shieldActivated 0)
					)
					(client setScript: BlowUp)
				)
				(= global594 0)
				(= global175 10)
			)
		)
	)
)

(instance Attack2 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== global594 2)
					(self changeState: 1)
				else
					(ShakeScreen 5 3)
					(= global594 1)
					(if
						(or
							(not (-- shipShieldHealth))
							(== shieldActivated 1)
							(== shieldActivated 0)
						)
						(curRoom setScript: BlowUp)
					)
					(= cycles 1)
				)
			)
			(1
				(= global594 2)
				(= seconds 6)
			)
			(2
				(ShakeScreen 5 shakeSDiagonal)
				(if
					(or
						(not (-- shipShieldHealth))
						(== shieldActivated 2)
						(== shieldActivated 0)
					)
					(curRoom setScript: BlowUp)
				)
				(= global175 10)
				(= global594 0)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance BlowUp of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ShakeScreen 20 3)
				(cast eachElementDo: #dispose)
				(cls)
				(curRoom drawPic: 121)
				(if (not shipShieldHealth)
					(Print 701 1)
				else
					(Print 701 2)
				)
				(EgoDead 0 0 4 5)
			)
		)
	)
)

(instance zipShip of Actor
	(method (init)
		(super init:)
		(self
			view: 51
			ignoreActors: TRUE
			setPri: 6
			illegalBits: 0
		)
	)
)

(instance boom of Sound
	(properties
		number 33
		priority 2
	)
)
