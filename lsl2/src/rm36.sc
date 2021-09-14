;;; Sierra Script 1.0 - (do not remove this comment)
(script# 36)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm36 0
)

(local
	local0
	aDials1
	aDials2
	aDials3
	aDials4
	aDials5
	aDials6
	aLever
	aCaptain
	aWheel
	aHench
	aDart
	aHorizonEast
	aHorizonWest
)
(instance rm36 of Room
	(properties
		picture 36
		south 31
	)
	
	(method (init)
		(Load VIEW currentEgoView)
		(Load VIEW 322)
		(Load VIEW 323)
		(Load VIEW 324)
		(Load VIEW 321)
		(super init:)
		((View new:)
			view: 322
			loop: 0
			cel: 0
			posn: 80 111
			setPri: 7
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 322
			loop: 0
			cel: 1
			posn: 94 106
			setPri: 7
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 322
			loop: 0
			cel: 1
			posn: 82 111
			setPri: 7
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 322
			loop: 0
			cel: 1
			posn: 71 116
			setPri: 8
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 322
			loop: 0
			cel: 2
			posn: 144 84
			setPri: 6
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 322
			loop: 0
			cel: 2
			posn: 152 84
			setPri: 6
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 322
			loop: 0
			cel: 2
			posn: 117 93
			setPri: 6
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 322
			loop: 0
			cel: 2
			posn: 98 100
			setPri: 6
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 322
			loop: 9
			cel: 0
			posn: 99 150
			setPri: 15
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 322
			loop: 9
			cel: 1
			posn: 229 150
			setPri: 15
			ignoreActors:
			addToPic:
		)
		((= aDials1 (Prop new:))
			view: 322
			setLoop: 1
			cel: 2
			posn: 201 92
			setPri: 6
			setCycle: Forward
			cycleSpeed: 1
			ignoreActors:
			isExtra: TRUE
			init:
		)
		((= aDials2 (Prop new:))
			view: 322
			setLoop: 2
			cel: 0
			posn: 199 84
			setPri: 6
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors:
			isExtra: TRUE
			init:
		)
		((= aDials3 (Prop new:))
			view: 322
			setLoop: 7
			cel: 0
			posn: 107 98
			setPri: 8
			setCycle: Forward
			cycleSpeed: 3
			ignoreActors:
			isExtra: TRUE
			init:
		)
		((= aDials4 (Prop new:))
			view: 322
			setLoop: 6
			setPri: 6
			posn: 101 104
			setCycle: Forward
			cycleSpeed: 5
			isExtra: TRUE
			init:
		)
		((= aDials5 (Prop new:))
			view: 322
			setLoop: 7
			cel: 0
			posn: 128 90
			setPri: 6
			setCycle: Forward
			cycleSpeed: 5
			ignoreActors:
			isExtra: TRUE
			init:
		)
		((= aDials6 (Prop new:))
			view: 322
			setLoop: 8
			cel: 0
			posn: 217 101
			setPri: 8
			setCycle: Forward
			cycleSpeed: 6
			ignoreActors:
			isExtra: TRUE
			init:
		)
		((= aHorizonEast (Prop new:))
			view: 322
			setLoop: 5
			setPri: 3
			posn: 181 68
			setCycle: Forward
			cycleSpeed: 3
			isExtra: TRUE
			init:
		)
		((= aHorizonWest (Prop new:))
			view: 322
			setLoop: 4
			setPri: 3
			posn: 68 68
			setCycle: Forward
			cycleSpeed: 3
			isExtra: TRUE
			init:
		)
		((= aLever (Prop new:))
			view: 322
			setLoop: 3
			setCel: (if lifeboatLeverPulled 255 else 0)
			setPri: 8
			posn: 190 102
			stopUpd:
			init:
		)
		((= aWheel (Prop new:))
			view: 323
			setLoop: 1
			cel: 0
			posn: 125 104
			setPri: 6
			init:
		)
		((= aCaptain (Actor new:))
			view: 323
			setLoop: 0
			cel: 0
			posn: 140 115
			setPri: 8
			init:
			setScript: captainScript
		)
		((= aHench (Actor new:))
			view: 321
			setLoop: 1
			setPri: 5
			posn: 192 91
			ignoreActors:
			illegalBits: 0
			setCycle: Walk
			init:
			hide:
		)
		(NormalEgo loopN)
		(ego posn: 164 151 init:)
		(self setRegions: SHIP setScript: rm36Script)
	)
)

(instance rm36Script of Script
	(method (doit)
		(super doit:)
		(cond 
			((& (ego onControl:) cBLUE)
				(curRoom newRoom: 31)
			)
			((and (< state 1) (< (ego y?) 111))
				(self changeState: 1)
			)
			((and (< state 9) (< (ego x?) 155))
				(self changeState: 9)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= seconds (= cycles 0))
				(HandsOff)
				(= currentStatus egoSTOPPED)
				(aHench show: setMotion: MoveTo 167 92 self)
				(captainScript dispose:)
				(ego stopUpd:)
			)
			(2
				(aHench
					setLoop: 2
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(3
				(aHench cycleSpeed: 0 setCycle: BegLoop)
				((= aDart (Actor new:))
					view: 321
					setLoop: 3
					posn: 169 76
					illegalBits: 0
					ignoreActors:
					setStep: 4 4
					init:
					setMotion: MoveTo 146 95 self
				)
				(Print 36 9 #at -1 20 #draw)
			)
			(4
				(aDart dispose:)
				(aHench
					setLoop: 0
					setCycle: Walk
					cycleSpeed: 0
					setMotion: MoveTo 192 92
				)
				(aWheel setCycle: EndLoop)
				(aCaptain
					view: 324
					setLoop: 0
					setCel: 0
					posn: 140 115
					setPri: 8
					ignoreActors:
					illegalBits: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(5
				((View new:)
					view: 324
					ignoreActors:
					setLoop: 2
					posn: 140 115
					setPri: 7
					addToPic:
				)
				(aCaptain view: 324 setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(6
				(Print 36 10)
				(aCaptain stopUpd:)
				(aWheel stopUpd:)
				(= seconds 3)
			)
			(7
				(Print 36 11)
				(Print 36 12)
				(aHench dispose:)
				(= seconds 3)
			)
			(8
				(ShakeScreen 8 (Random 1 3))
				(Print 36 13)
				(Print 36 14)
				(= currentStatus egoDYING)
			)
			(9
				(= seconds (= cycles 0))
				(= currentStatus egoSTOPPED)
				(HandsOff)
				(ego stopUpd:)
				(Print 36 15 #draw)
				(= seconds 3)
			)
			(10
				(ego hide:)
				(Print 36 16 #draw)
				(= currentStatus egoDYING)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/console,buffet,(console<control)')
				(Print 36 0)
			)
			(if (Said '/control,computer,amp,burn,krod')
				(Print 36 1)
			)
			(if (Said '/cord,cord')
				(Print 36 2)
			)
			(if (Said '/man')
				(Print 36 3)
			)
			(if (Said '[/airport,cabin]')
				(Print 36 4)
				(Print 36 5)
			)
		)
		(if (Said '/man,man')
			(Print 36 6)
		)
		(if (Said 'throw,cord,jerk/cord')
			(cond 
				(lifeboatLeverPulled
					(Print 36 7)
				)
				((not (ego inRect: 175 110 202 128))
					(NotClose)
				)
				(else
					(= lifeboatLeverPulled TRUE)
					(theGame changeScore: 8)
					(aLever setCycle: EndLoop)
					(Print 36 8 #draw #at -1 15 #width 280)
					(SetRegionTimer rgLIFEBOATS 2 10)
				)
			)
		)
	)
)

(instance captainScript of Script
	(method (changeState newState &tmp theSpeed temp1)
		(switch (= state newState)
			(0
				(= cycles (Random 5 10))
			)
			(1
				(= theSpeed (Random 0 3))
				(aCaptain cycleSpeed: theSpeed setCycle: EndLoop)
				(aWheel cycleSpeed: theSpeed setCycle: EndLoop)
				(= cycles (Random 5 10))
			)
			(2
				(aCaptain setCel:)
				(aWheel setCel:)
				(= cycles (Random 5 10))
			)
			(3
				(= theSpeed (Random 0 3))
				(aCaptain cycleSpeed: theSpeed setCycle: BegLoop)
				(aWheel cycleSpeed: theSpeed setCycle: BegLoop)
				(= cycles (Random 5 10))
			)
			(4
				(aCaptain setCel:)
				(aWheel setCel:)
				(self changeState: 0)
			)
		)
	)
)
