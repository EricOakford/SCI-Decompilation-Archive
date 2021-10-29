;;; Sierra Script 1.0 - (do not remove this comment)
(script# 131)
(include sci.sh)
(use Main)
(use Intrface)
(use AutoDoor)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm131 0
)

(local
	door
	bains
	[sewage 5]
)
(instance bainsShot of Sound
	(properties
		number 41
		priority 10
	)
)

(instance rm131 of Room
	(properties
		picture 76
		style $0000
	)
	
	(method (init)
		(self setRegions: 205)
		(super init:)
		((= door (AutoDoor new:))
			doorControl: 4096
			entranceTo: 133
			facingLoop: 3
			view: 293
			loop: 0
			posn: 184 82
			setPri: 1
			stopUpd:
			init:
		)
		(ego
			view: (if (not gunDrawn) 0 else 6)
			x:
			(switch prevRoomNum
				(130 10)
				(133 180)
				(132 310)
			)
			y:
				(cond 
					((== prevRoomNum 133) 85)
					((<= (ego y?) 115) 100)
					(else 140)
				)
			init:
		)
		(HandsOn)
		((= [sewage 0] (Prop new:))
			view: 99
			loop: 0
			cel: 1
			posn: 319 131
			setPri: 1
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		((= [sewage 1] (Prop new:))
			view: 99
			loop: 0
			cel: 2
			posn: 237 130
			setPri: 1
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		((= [sewage 2] (Prop new:))
			view: 99
			loop: 0
			cel: 1
			posn: 120 131
			setPri: 1
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		((= [sewage 3] (Prop new:))
			view: 99
			loop: 0
			cel: 2
			posn: 133 173
			setPri: 1
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		((= [sewage 4] (Prop new:))
			view: 99
			loop: 0
			cel: 0
			posn: 192 125
			setPri: 1
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		(if (< howFast 60)
			([sewage 0] stopUpd:)
			([sewage 2] stopUpd:)
		)
		(if (< howFast 30)
			([sewage 1] stopUpd:)
			([sewage 3] stopUpd:)
			([sewage 4] stopUpd:)
		)
		(sewerLight posn: 167 54 ignoreActors: 1 init: stopUpd:)
		(if untiedMarie
			(Load rsVIEW 13)
			(Load rsVIEW 15)
			((= bains (Actor new:))
				view: 13
				posn: 110 100
				loop: 0
				cel: 0
				init:
				setCycle: Walk
				setScript: bainsKillsScript
			)
		)
	)
	
	(method (doit)
		(cond 
			(sewerCutscene 0)
			((<= (ego x?) 5) (curRoom newRoom: 130))
			((>= (ego x?) 315) (curRoom newRoom: 132))
			((== (door doorState?) 2)
				(ego heading: 0 setMotion: MoveTo 180 10)
				(curRoom newRoom: 133)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 301)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said '[<around][/(!*,chamber,sewer)]') (Print 131 0) (Print 131 1))
					((Said 'look/door') (Print 131 2))
					((Said 'open/door') (Print 131 3))
					((Said '*/door') (Print 131 4))
				)
			)
		)
	)
)

(instance bainsKillsScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bains setMotion: MoveTo 130 100 self)
				(HandsOff)
			)
			(1
				(bains view: 15 loop: 0 cel: 0 setCycle: EndLoop self)
				(bainsShot play:)
			)
			(2
				(ego
					view: 297
					illegalBits: 0
					x: 184
					loop: 3
					setCycle: EndLoop self
				)
			)
			(3 (EgoDead 131 5))
		)
	)
)
