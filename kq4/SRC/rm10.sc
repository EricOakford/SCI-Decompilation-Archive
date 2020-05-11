;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room10 0
)

(local
	[local0 2]
	local2
	local3
	smoke
	bird1
	bird2
)
(instance ogreTheme of Sound
	(properties
		number 5
		loop -1
	)
)

(instance Room10 of Room
	(properties
		picture 10
	)
	
	(method (init)
		(= north 4)
		(= south 16)
		(= east 11)
		(= west 9)
		(= horizon 72)
		(= isIndoors FALSE)
		(= noWearCrown 1)
		(super init:)
		(if isNightTime (curRoom overlay: 110))
		(self setRegions: FOREST OGRE)
		(switch prevRoomNum
			(16
				(if (< (ego x?) 63)
					(ego posn: 63 187)
				else
					(ego posn: (ego x?) 187)
				)
			)
			(4
				(if (< (ego x?) 200)
					(ego posn: 205 (+ horizon 2))
				else
					(ego posn: (ego x?) (+ horizon 2))
				)
			)
		)
		(= smoke (Prop new:))
		(smoke
			isExtra: TRUE
			view: 626
			loop: 0
			cel: 0
			posn: 233 25
			setPri: 0
			setCycle: Forward
			cycleSpeed: 3
			init:
		)
		(cond 
			(
			(and (== prevRoomNum 4) ogre (not (ego has: iMagicHen)))
				(= ogre (Actor new:))
				(Load VIEW 78)
				(Load VIEW 79)
				(ogre
					posn: 202 84
					view: 250
					xStep: 6
					yStep: 2
					setCycle: Walk
					ignoreActors:
					init:
				)
				(= local2 0)
				(self setScript: ogreActions)
				(ogreActions changeState: 1)
			)
			(
				(and
					(<= (Random 1 100) 40)
					(not ogre)
					(!= ogreState 4)
				)
				(Load VIEW 78)
				(Load VIEW 79)
				(= ogre (Actor new:))
				(ogre
					posn: 19 181
					view: 250
					xStep: 6
					yStep: 2
					setCycle: Walk
					ignoreActors:
					init:
				)
				(self setScript: ogreActions)
			)
			(else (= ogre 0))
		)
		(if (== ogreState 4) (= ogreState 5))
		(ego view: 2 init:)
		(if (not isNightTime)
			(if (< (Random 1 100) 50)
				(= bird1 (Prop new:))
				(bird1
					view: 342
					loop: 5
					cel: 1
					posn: 146 10
					setPri: 14
					setScript: bird1Actions
					init:
				)
			)
			(if (< (Random 1 100) 50)
				(= bird2 (Prop new:))
				(bird2
					view: 342
					loop: 4
					cel: 1
					posn: 65 54
					setPri: 14
					setScript: bird2Actions
					init:
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if
			(and (== (event type?) saidEvent) (Said 'look>'))
				(cond 
					((Said '/cottage') (Print 10 0))
					((Said '[<around][/room]') (Print 10 1))
				)
			else
				0
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(= noWearCrown 0)
		(timers eachElementDo: #dispose 84)
		(if (cast contains: ogre) (= ogreY (ogre y?)))
		(super newRoom: newRoomNumber)
	)
)

(instance ogreActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 1 6)))
			(1
				(if (== local2 0)
					(= local2 1)
					(ogreTheme play:)
					(ogre
						setAvoider: (Avoider new:)
						ignoreActors:
						setMotion: Chase ego 6 self
					)
					(= noWearCrown TRUE)
				)
			)
			(2
				(HandsOff)
				(ego hide:)
				(ogre view: 78 cel: 255 setCycle: EndLoop self)
			)
			(3
				(ogreTheme dispose:)
				(ogreTheme number: 6 loop: 1 play: self)
				(Print 10 2)
				(ogre view: 79 setCycle: Walk setMotion: MoveTo 200 80)
			)
			(4 (= dead TRUE))
		)
	)
)

(instance bird1Actions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bird1 setCycle: Forward)
				(= seconds (Random 1 12))
			)
			(1
				(bird1 setCycle: 0 cel: 0)
				(= seconds (Random 1 8))
			)
			(2 (self changeState: 0))
		)
	)
)

(instance bird2Actions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bird2 setCycle: Forward)
				(= seconds (Random 1 12))
			)
			(1
				(bird2 cel: 0 setCycle: 0)
				(= seconds (Random 1 8))
			)
			(2 (self changeState: 0))
		)
	)
)
