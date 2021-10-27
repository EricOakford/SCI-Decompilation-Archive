;;; Sierra Script 1.0 - (do not remove this comment)
(script# 124)
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
	rm124 0
)

(local
	climbedUpLadder
	triedToOpenManhole
	bainsIsHere
	bains
	[sewage 3]
)
(instance bainsMusic of Sound
	(properties
		number 10
		priority 8
	)
)

(instance bainsGunFire of Sound
	(properties
		number 41
		priority 9
	)
)

(instance rm124 of Room
	(properties
		picture 203
		style $0000
	)
	
	(method (init)
		(super init:)
		(self setRegions: 205)
		(Load rsVIEW 295)
		(Load rsVIEW 15)
		(Load rsVIEW 298)
		(ego
			x:
				(cond 
					((== prevRoomNum 122) (if (<= (ego x?) 220) 60 else 133))
					((<= (ego x?) 120) 163)
					(else 292)
				)
			y: (if (== prevRoomNum 122) 87 else 180)
			view: (if (not gunDrawn) 0 else 6)
			init:
		)
		(HandsOn)
		((View new:)
			view: 295
			loop: 5
			cel: 0
			posn: 175 104
			ignoreActors: 1
			init:
			addToPic:
		)
		((Prop new:)
			view: 260
			loop: 8
			cel: 1
			posn: 294 173
			setPri: 0
			ignoreActors: 1
			init:
			addToPic:
		)
		((= [sewage 0] (Prop new:))
			view: 99
			loop: 2
			cel: 0
			posn: 205 189
			setPri: 0
			setCycle: Forward
			cycleSpeed: 3
			ignoreActors: 1
			init:
		)
		((= [sewage 1] (Prop new:))
			view: 99
			loop: 2
			cel: 2
			posn: 96 113
			setPri: 0
			setCycle: Forward
			cycleSpeed: 3
			ignoreActors: 1
			init:
		)
		((= [sewage 2] (Prop new:))
			view: 99
			loop: 2
			cel: 1
			posn: 175 177
			setPri: 0
			setCycle: Forward
			cycleSpeed: 3
			ignoreActors: 1
			init:
		)
		(if (< howFast 60)
			([sewage 0] stopUpd:)
			([sewage 1] stopUpd:)
		)
		(if (< howFast 30) ([sewage 2] stopUpd:))
		(sewerRat
			name: 6
			setLoop: 4
			illegalBits: 0
			posn: 232 93
			ignoreActors: 1
			init:
			setMotion: MoveTo 400 149 sewerRat
		)
		(sewerLight
			posn: 75 29
			ignoreActors: 1
			setPri: 14
			init:
			stopUpd:
		)
		(sewerLight2 posn: 155 59 ignoreActors: 1 init: stopUpd:)
	)
	
	(method (doit)
		(cond 
			(sewerCutscene 0)
			((<= (ego y?) 85) (curRoom newRoom: 122))
			((>= (ego y?) 190) (curRoom newRoom: 127))
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said 'look/ladder') (Print 124 0))
					((Said 'open,remove,press,move/cover')
						(if (not climbedUpLadder)
							(Print 124 1)
						else
							(Print 124 2)
							(= triedToOpenManhole 1)
						)
					)
					((Said 'climb[/ladder]>')
						(cond 
							((Said '<down')
								(if (not climbedUpLadder)
									(Print 124 3)
								else
									(ego setScript: ladderScript)
									(ladderScript changeState: 3)
								)
							)
							((Said '<up')
								(cond 
									((!= climbedUpLadder 0) (Print 124 4))
									((& (ego onControl: 1) $0080)
										(ego setScript: ladderScript)
										(ladderScript changeState: 1)
									)
									(else (NotClose))
								)
							)
							((Said '[<!*]')
								(cond 
									(climbedUpLadder
										(ego setScript: ladderScript)
										(ladderScript changeState: 3)
									)
									((& (ego onControl: 1) $0080) (ladderScript changeState: 1))
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
			(1
				(HandsOff)
				(= climbedUpLadder 1)
				(= gunDrawn 0)
				(ego
					view: 295
					loop: 1
					cel: 0
					ignoreActors: 1
					illegalBits: 0
					posn: 176 104
					setCycle: CycleTo 11 1 self
					setMotion: 0
				)
			)
			(2
				(Print 124 5)
				(User canInput: 1)
			)
			(3
				(ego loop: 3 setCycle: CycleTo 7 1 self)
				(User canInput: 0)
			)
			(4
				(HandsOn)
				(ego
					view: (if (not gunDrawn) 0 else 6)
					posn: 152 100
					loop: 2
					cel: 0
					setCycle: Walk
					illegalBits: -32768
					ignoreActors: 0
				)
				(= climbedUpLadder 0)
				(self changeState: 0)
				(if (and (not bainsIsHere) triedToOpenManhole)
					(= triedToOpenManhole 0)
					(= bainsIsHere 1)
					(bainsMusic play:)
					((= bains (Actor new:))
						view: 14
						setLoop: 1
						cel: 0
						illegalBits: 0
						ignoreActors:
						posn: 330 230
						setStep: 6 3
						init:
						setCycle: Walk
						setScript: ambushScript
					)
				)
				(ego setScript: 0)
			)
		)
	)
)

(instance ambushScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 20 30)))
			(1
				(if isHandsOff
					(= cycles 4)
					(-- state)
				else
					(HandsOff)
					(bains setMotion: MoveTo 290 190 self)
					(ego setCycle: 0)
				)
			)
			(2
				(bains view: 15 cel: 0 setCycle: EndLoop self)
				(bainsGunFire play:)
			)
			(3
				(if (or gunDrawn (>= (Random 0 10) 5))
					(self changeState: 10)
				else
					(self changeState: 4)
				)
			)
			(4
				(ego
					illegalBits: 0
					view: 298
					loop: 1
					posn: (- (ego x?) 5) (ego y?)
				)
				(bains view: 13)
				(RedrawCast)
				(EgoDead 124 6)
			)
			(10
				(bains
					view: 14
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 320 215 self
				)
			)
			(11
				(bainsMusic stop:)
				(= cycles 4)
			)
			(12
				(HandsOn)
				(ego setMotion: 0 setCycle: Walk)
				(Print 124 7)
				(bains dispose:)
			)
		)
	)
)
