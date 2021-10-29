;;; Sierra Script 1.0 - (do not remove this comment)
(script# 130)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm130 0
)

(local
	pipe
	[sewage 3]
	atDeadEnd
	nearDrainPipe
)
(instance rm130 of Room
	(properties
		picture 201
		style $0000
	)
	
	(method (init)
		(super init:)
		(self setRegions: 205)
		(HandsOn)
		(ego
			view: (if (not gunDrawn) 0 else 6)
			x:
				(switch prevRoomNum
					(128
						(if (<= (ego x?) 70) 165 else 237)
					)
					(131 310)
				)
			y:
				(switch prevRoomNum
					(128
						(if (<= (ego x?) 70) 100 else 95)
					)
					(131
						(if (<= (ego y?) 115) 100 else 135)
					)
				)
			init:
		)
		((= pipe (Prop new:))
			view: 92
			loop: 0
			cel: 0
			posn: 286 117
			setPri: 3
			ignoreActors: 1
			init:
			stopUpd:
		)
		((= [sewage 0] (Prop new:))
			view: 99
			loop: 1
			cel: 2
			posn: 317 130
			setPri: 1
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		((= [sewage 1] (Prop new:))
			view: 99
			loop: 3
			cel: 0
			posn: 176 141
			setPri: 1
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		((= [sewage 2] (Prop new:))
			view: 99
			loop: 3
			cel: 1
			posn: 133 173
			setPri: 1
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors: 1
			init:
		)
		(if (< howFast 60) ([sewage 0] stopUpd:))
		(if (< howFast 30)
			([sewage 1] stopUpd:)
			([sewage 2] stopUpd:)
		)
		(sewerRat
			name: 2
			setLoop: 3
			illegalBits: 0
			posn: 249 65
			ignoreActors: 1
			init:
			setMotion: MoveTo 400 65 sewerRat
		)
		(= nearDrainPipe 0)
		(sewerLight posn: 127 78 ignoreActors: 1 init: stopUpd:)
		(sewerLight2 posn: 226 56 ignoreActors: 1 init: stopUpd:)
	)
	
	(method (doit)
		(cond 
			(sewerCutscene 0)
			((>= (ego x?) 315) (curRoom newRoom: 131))
			((ego inRect: 175 85 250 94) (curRoom newRoom: 128))
			((ego inRect: 0 190 150 200)
				(if (not atDeadEnd)
					(= atDeadEnd 1)
					(Print 130 0)
					(ego setMotion: MoveTo (ego x?) 180)
				)
			)
			(
			(and (ego inRect: 280 92 290 108) (not nearDrainPipe))
				(= nearDrainPipe 1)
				(if (== (Random 0 4) 3)
					(= sewerCutscene 1)
					(ego setScript: drainPipeScript)
				)
			)
			(else (= nearDrainPipe 0) (= atDeadEnd 0))
		)
		(super doit:)
	)
)

(instance drainPipeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 92
					setLoop: 1
					cel: 0
					yStep: 1
					illegalBits: 0
					setMotion: MoveTo 304 110
					setCycle: CycleTo 2 1
				)
				(pipe setCycle: CycleTo 3 1 self)
				(cSound stop: number: 24 loop: 1 priority: 12 play:)
			)
			(1
				(ego yStep: 3 setMotion: MoveTo 304 115 setCycle: CycleTo 4 1)
				(pipe setCycle: CycleTo 6 1 self)
			)
			(2
				(ego setMotion: MoveTo 304 125 setCycle: CycleTo 7 1)
				(pipe setCycle: CycleTo 10 1)
				(Timer setCycle: self 10)
			)
			(3
				(Print 130 1)
				(Print 130 2)
				(Print 130 3)
				(ego dispose:)
				(EgoDead 130 4)
			)
		)
	)
)
