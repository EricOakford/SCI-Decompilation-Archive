;;; Sierra Script 1.0 - (do not remove this comment)
(script# 90)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room90 0
)

(local
	local0
)
(instance fallMusic of Sound
	(properties
		number 51
	)
)

(instance Room90 of Room
	(properties
		picture 90
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load SCRIPT JUMP)
		(Load VIEW 634)
		(Load VIEW 512)
		(Load SOUND 51)
		(self setRegions: LOLOTTE)
		(super init:)
		(NotifyScript LOLOTTE 0)
		((View new:)
			view: 634
			loop: 1
			cel: 1
			posn: 209 77
			setPri: 4
			init:
			addToPic:
		)
		((Prop new:)
			view: 512
			loop: 0
			posn: 209 65
			setPri: 3
			init:
			setCycle: Forward
		)
		(if (or (== prevRoomNum 91) (== prevRoomNum 0))
			(ego
				posn: 255 134
				baseSetter: 0
				view: 4
				setStep: 4 2
				illegalBits: -16384
				init:
			)
			(= local0 0)
			(if henchChasingEgo
				(Print 90 0 #at -1 10 #font smallFont)
				(= henchChasingEgo FALSE)
			)
		)
		(if (== prevRoomNum 85)
			(ego
				posn: 104 38
				baseSetter: (ScriptID 0 1)
				view: 4
				setStep: 4 2
				illegalBits: cWHITE
				setPri: 12
				init:
			)
			(= local0 1)
		)
	)
	
	(method (doit)
		(if (& (ego onControl: 0) cBROWN)
			(ego setPri: -1 illegalBits: cWHITE baseSetter: 0)
			(curRoom newRoom: 91)
		)
		(if (& (ego onControl: 0) cMAGENTA)
			(ego setPri: -1 illegalBits: cWHITE baseSetter: 0)
			(curRoom newRoom: 85)
		)
		(if
			(and
				(!= (self script?) fallingToDeath)
				(!= (self script?) fallingDown)
			)
			(if local0
				(cond 
					((& (ego onControl:) cGREEN) (self setScript: fallingToDeath))
					((& (ego onControl:) cGREY) (self setScript: fallingDown))
				)
			)
			(if (& (ego onControl: origin) cBLUE)
				(if
				(and (< (ego heading?) 180) (> (ego heading?) 0))
					(ego
						baseSetter: (ScriptID 0 1)
						setPri: -1
						illegalBits: -16384
					)
					(= local0 0)
				else
					(ego baseSetter: 0 illegalBits: cWHITE setPri: 12)
					(= local0 1)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript JUMP)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				(
				(and (== (event type?) saidEvent) (Said 'look>'))
					(cond 
						((or (Said '/dirt') (Said '<down')) (Print 90 1))
						((Said '/wall') (Print 90 2))
						((or (Said '/sky') (Said '<up')) (Print 90 3))
						((Said '/stair') (Print 90 4))
						((Said '/stair') (Print 90 5))
						((Said '/door') (Print 90 6))
						((Said '/door') (Print 90 7))
						(
						(or (Said '[<around][/room]') (Said '/castle,tower')) (Print 90 8))
						(else (event claimed: FALSE))
					)
				)
			)
		)
	)
)

(instance fallingDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canInput: 0 canControl: 0)
				(ego
					view: 44
					loop: 0
					illegalBits: 0
					setPri: 9
					setCycle: EndLoop
					setMotion:
						((Jump new:)
							y: 145
							gx: 2
							gy: (if (>= (ego y?) 145) -5 else 5)
						)
						self
				)
				(fallMusic play:)
			)
			(1
				(ego view: 41 loop: 0 cel: 255 setCycle: EndLoop self)
			)
			(2
				(ego
					view: 4
					baseSetter: 0
					illegalBits: -16384
					setPri: -1
					setCycle: Walk
				)
				(= local0 0)
				(fallMusic dispose:)
				(User canInput: TRUE canControl: TRUE)
				(client setScript: 0)
				(= state -1)
			)
		)
	)
)

(instance fallingToDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canInput: FALSE canControl: FALSE)
				(= currentStatus egoFallDownStairs)
				(ego
					view: 44
					loop: 2
					illegalBits: 0
					setPri: 9
					setMotion: ((Jump new:) y: 145 gx: 2 gy: 5) self
				)
				(fallMusic play:)
			)
			(1
				(ego view: 42)
				(RedrawCast)
				(ShakeScreen 5)
				(= seconds 2)
			)
			(2
				(sounds eachElementDo: #stop 0)
				(= dead TRUE)
				(client setScript: 0)
			)
		)
	)
)
