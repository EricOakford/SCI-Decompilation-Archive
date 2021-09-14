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
		(addToPics
			add:
				aView1
				aView2
				aView3
				aView4
				aView5
				aView6
				aView7
				aView8
				aView9
				aView10
			doit:
		)
		(aDials1
			loop: 1
			setPri: 6
			setCycle: Forward
			cycleSpeed: 1
			isExtra: TRUE
			init:
		)
		(aDials2
			setPri: 6
			setCycle: Forward
			cycleSpeed: 2
			isExtra: TRUE
			init:
		)
		(aDials3
			setPri: 8
			setCycle: Forward
			cycleSpeed: 3
			isExtra: TRUE
			init:
		)
		(aDials4
			setPri: 6
			setCycle: Forward
			cycleSpeed: 5
			isExtra: TRUE
			init:
		)
		(aDials5
			setPri: 6
			setCycle: Forward
			cycleSpeed: 5
			isExtra: TRUE
			init:
		)
		(aDials6
			setPri: 8
			setCycle: Forward
			cycleSpeed: 6
			isExtra: TRUE
			init:
		)
		(aHorizonEast
			setPri: 3
			setCycle: Forward
			cycleSpeed: 3
			isExtra: TRUE
			init:
		)
		(aHorizonWest
			setPri: 3
			setCycle: Forward
			cycleSpeed: 3
			isExtra: TRUE
			init:
		)
		(aLever
			setCel: (if lifeboatLeverPulled 255 else 0)
			setPri: 8
			stopUpd:
			init:
		)
		(aWheel
			setPri: 6
			init:
		)
		(aCaptain
			setPri: 8
			init:
			setScript: captainScript
		)
		(aHench
			setLoop: 1
			setPri: 5
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
				(aDart
					setLoop: 3
					illegalBits: 0
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
				(addToPics add: aDying doit:)
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

(instance aView1 of PicView
	(properties
		y 111
		x 80
		view 322
		priority 7
		signal ignrAct
	)
)

(instance aView2 of PicView
	(properties
		y 106
		x 94
		view 322
		cel 1
		priority 7
		signal ignrAct
	)
)

(instance aView3 of PicView
	(properties
		y 111
		x 82
		view 322
		cel 1
		priority 7
		signal ignrAct
	)
)

(instance aView4 of PicView
	(properties
		y 116
		x 71
		view 322
		cel 1
		priority 8
		signal ignrAct
	)
)

(instance aView5 of PicView
	(properties
		y 84
		x 144
		view 322
		cel 2
		priority 6
		signal ignrAct
	)
)

(instance aView6 of PicView
	(properties
		y 84
		x 152
		view 322
		cel 2
		priority 6
		signal ignrAct
	)
)

(instance aView7 of PicView
	(properties
		y 93
		x 117
		view 322
		cel 2
		priority 6
		signal ignrAct
	)
)

(instance aView8 of PicView
	(properties
		y 100
		x 98
		view 322
		cel 2
		priority 6
		signal ignrAct
	)
)

(instance aView9 of PicView
	(properties
		y 150
		x 99
		view 322
		loop 9
		priority 15
		signal ignrAct
	)
)

(instance aView10 of PicView
	(properties
		y 150
		x 229
		view 322
		loop 9
		cel 1
		priority 15
		signal ignrAct
	)
)

(instance aDying of PicView
	(properties
		y 115
		x 140
		view 324
		loop 2
		priority 7
		signal ignrAct
	)
)

(instance aDials1 of Prop
	(properties
		y 92
		x 201
		view 322
		cel 2
		signal ignrAct
	)
)

(instance aDials2 of Prop
	(properties
		y 84
		x 199
		view 322
		loop 2
		signal ignrAct
	)
)

(instance aDials3 of Prop
	(properties
		y 98
		x 107
		view 322
		loop 7
		signal ignrAct
	)
)

(instance aDials4 of Prop
	(properties
		y 104
		x 101
		view 322
		loop 6
	)
)

(instance aDials5 of Prop
	(properties
		y 90
		x 128
		view 322
		loop 7
		signal ignrAct
	)
)

(instance aDials6 of Prop
	(properties
		y 101
		x 217
		view 322
		loop 8
		signal ignrAct
	)
)

(instance aHorizonEast of Prop
	(properties
		y 68
		x 181
		view 322
		loop 5
	)
)

(instance aHorizonWest of Prop
	(properties
		y 68
		x 68
		view 322
		loop 4
	)
)

(instance aLever of Prop
	(properties
		y 102
		x 190
		view 322
		loop 3
	)
)

(instance aWheel of Prop
	(properties
		y 104
		x 125
		view 323
		loop 1
	)
)

(instance aCaptain of Actor
	(properties
		y 115
		x 140
		view 323
	)
)

(instance aHench of Actor
	(properties
		y 91
		x 192
		view 321
		signal ignrAct
	)
)

(instance aDart of Actor
	(properties
		y 76
		x 169
		view 321
		signal ignrAct
	)
)
