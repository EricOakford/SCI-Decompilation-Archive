;;; Sierra Script 1.0 - (do not remove this comment)
(script# 121)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm121 0
)

(local
	pipe
	[pipeSewage 4]
	local5
)
(instance rm121 of Room
	(properties
		picture 70
		style $0000
	)
	
	(method (init)
		(super init:)
		(self setRegions: 205)
		(ego
			view: (if (not gunDrawn) 0 else 6)
			x: (if (== prevRoomNum 120) 12 else 310)
			y: (if (<= (ego y?) 115) 100 else 140)
			init:
			forceUpd:
		)
		(if (== prevRoomNum 122) (= methaneGasTimer -1))
		(HandsOn)
		((= pipe (Prop new:))
			view: 92
			loop: 0
			cel: 0
			posn: 305 115
			setPri: 1
			ignoreActors: 1
			init:
			stopUpd:
		)
		((Prop new:)
			view: 92
			loop: 2
			cel: 3
			posn: 114 116
			setPri: 1
			setCycle: Forward
			ignoreActors: 1
			init:
		)
		((= [pipeSewage 0] (Prop new:))
			view: 99
			loop: 0
			cel: 1
			posn: 319 126
			setPri: 1
			setCycle: Forward
			cycleSpeed: 4
			ignoreActors:
			init:
		)
		((= [pipeSewage 1] (Prop new:))
			view: 99
			loop: 0
			cel: 2
			posn: 237 130
			setPri: 1
			setCycle: Forward
			cycleSpeed: 4
			ignoreActors:
			init:
		)
		((= [pipeSewage 2] (Prop new:))
			view: 99
			loop: 0
			cel: 1
			posn: 120 126
			setPri: 1
			setCycle: Forward
			cycleSpeed: 4
			ignoreActors:
			init:
		)
		((= [pipeSewage 3] (Prop new:))
			view: 99
			loop: 0
			cel: 0
			posn: 207 132
			setPri: 1
			setCycle: Forward
			cycleSpeed: 4
			ignoreActors:
			init:
		)
		(if (< howFast 60)
			([pipeSewage 0] stopUpd:)
			([pipeSewage 1] stopUpd:)
		)
		(if (< howFast 30)
			([pipeSewage 2] stopUpd:)
			([pipeSewage 3] stopUpd:)
		)
		(sewerRat
			name: 7
			posn: 287 57
			setLoop: 2
			ignoreActors: 1
			init:
			setMotion: MoveTo -100 57 sewerRat
		)
		(sewerLight posn: 167 54 ignoreActors: 1 stopUpd: init:)
		(= local5 0)
	)
	
	(method (doit)
		(cond 
			(sewerCutscene 0)
			((<= (ego x?) 5) (curRoom newRoom: 120))
			((>= (ego x?) 315) (curRoom newRoom: 122))
			((ego inRect: 292 92 303 108)
				(if (not local5)
					(= local5 1)
					(if (and (== (Random 0 4) 3) (not sewerCutscene))
						(= sewerCutscene 1)
						(ego setScript: downDrainPipe)
					)
				)
			)
			(else (= local5 0))
		)
		(super doit:)
	)
	
	(method (dispose)
		(downDrainPipe dispose:)
		(super dispose:)
	)
)

(instance downDrainPipe of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= sewerCutscene 1)
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
				(= cycles 10)
			)
			(3
				(Print 121 0)
				(Print 121 1)
				(Print 121 2)
				(EgoDead 121 3)
			)
		)
	)
)
