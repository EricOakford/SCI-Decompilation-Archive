;;; Sierra Script 1.0 - (do not remove this comment)
(script# 120)
(include sci.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm120 0
)

(local
	climbedUpLadder
	sewerRat2
	[sewage 3]
)
(instance sewerEntranceMusic of Sound
	(properties)
)

(instance rm120 of Room
	(properties
		picture 201
		style $0008
	)
	
	(method (init)
		(self setRegions: 205)
		(super init:)
		(HandsOn)
		(Load rsVIEW 295)
		(Load rsVIEW 301)
		(if (== prevRoomNum 81)
			(ego
				view: 295
				loop: 2
				cel: 0
				posn: 130 113
				illegalBits: -32768
				init:
			)
		else
			(ego
				view: (if (not gunDrawn) 0 else 6)
				x:
					(switch prevRoomNum
						(123
							(if (>= (ego x?) 235) 140 else 35)
						)
						(121 310)
						(else  130)
					)
				y:
				(switch prevRoomNum
					(123 180)
					(121 (ego y?))
					(else  120)
				)
				illegalBits: -32768
				init:
			)
		)
		((View new:)
			view: 295
			loop: 4
			cel: 0
			posn: 130 113
			ignoreActors: 1
			init:
			addToPic:
		)
		((View new:)
			view: 301
			loop: 0
			cel: 0
			posn: 211 90
			setPri: 1
			init:
			addToPic:
		)
		((= [sewage 0] (Prop new:))
			view: 99
			loop: 1
			cel: 0
			posn: 260 128
			setPri: 1
			setCycle: Forward
			cycleSpeed: 4
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
			cycleSpeed: 4
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
			cycleSpeed: 4
			ignoreActors: 1
			init:
		)
		((Prop new:)
			view: 99
			loop: 1
			cel: 2
			posn: 317 130
			setPri: 1
			ignoreActors: 1
			init:
			addToPic:
		)
		(if (< howFast 60) ([sewage 0] stopUpd:))
		(if (< howFast 30)
			([sewage 1] stopUpd:)
			([sewage 2] stopUpd:)
		)
		(= sewerRat2 (sewerRat new:))
		(sewerRat
			name: 2
			setLoop: 3
			illegalBits: 0
			posn: 380 65
			ignoreActors: 1
			init:
			setMotion: MoveTo 400 65 sewerRat
		)
		(sewerRat2
			name: 1
			setLoop: 5
			illegalBits: 0
			posn: 102 91
			ignoreActors: 1
			init:
			setMotion: MoveTo -100 172 sewerRat2
		)
		(sewerLight
			posn: 122 78
			ignoreActors: 1
			setPri: 14
			stopUpd:
			init:
		)
		(sewerLight2 posn: 226 56 ignoreActors: 1 stopUpd: init:)
		(if (== prevRoomNum 81)
			(= methaneGasTimer -1)
			(= gMethaneGasTimer -1)
			(sewerEntranceMusic number: 11 loop: 1 priority: 3 play:)
			(cSound number: 9 loop: -1 priority: 2 play:)
			(ego setScript: ladderScript)
			(ladderScript changeState: 1)
		)
	)
	
	(method (doit)
		(cond 
			(sewerCutscene 0)
			((>= (ego x?) 315) (curRoom newRoom: 121))
			((>= (ego y?) 190) (curRoom newRoom: 123))
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said 'look[<at,around][/!*,chamber,sewer]') (Print 120 0))
					((Said 'look/ladder') (Print 120 1))
					((Said 'climb[/ladder]>')
						(cond 
							((Said '<down')
								(if (not climbedUpLadder)
									(Print 120 2)
								else
									(ego setScript: ladderScript)
									(ladderScript changeState: 5)
								)
							)
							((Said '<up')
								(cond 
									((!= climbedUpLadder 0) (Print 120 3))
									((& (ego onControl: 1) $0080)
										(ego setScript: ladderScript)
										(ladderScript changeState: 3)
									)
									(else (NotClose))
								)
							)
							((Said '[<!*]')
								(cond 
									(climbedUpLadder
										(ego setScript: ladderScript)
										(ladderScript changeState: 5)
									)
									((& (ego onControl: 1) $0080) (ladderScript changeState: 3))
									(else (NotClose))
								)
							)
						)
					)
				)
			)
		)
	)
)

(instance ladderScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1 (HandsOff) (= seconds 2))
			(2 (self changeState: 5))
			(3
				(HandsOff)
				(= gunDrawn 1)
				(User canInput: 1)
				(= climbedUpLadder 1)
				(ego ignoreActors: 1)
				(ego
					view: 295
					loop: 0
					posn: 130 113
					setCycle: CycleTo 11 1 self
				)
			)
			(4
				(cSound stop:)
				(curRoom newRoom: 81)
			)
			(5
				(ego loop: 2 setCycle: CycleTo 7 1 self)
			)
			(6
				(ego
					view: (if (not gunDrawn) 0 else 6)
					posn: 154 108
					loop: 2
					cel: 0
					setCycle: Walk
					ignoreActors: 0
					illegalBits: -32768
				)
				(HandsOn)
				(= climbedUpLadder 0)
				(ego setScript: 0)
			)
		)
	)
)
