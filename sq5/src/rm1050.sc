;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1050)
(include game.sh) (include "1050.shm")
(use Main)
(use Scaler)
(use Osc)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm1050 0
)

(local
	local0
	local1
	theGOldCast
)
(instance rm1050 of Room
	(properties
		picture 129
		style (| BLACKOUT FADEOUT)
	)
	
	(method (init)
		(LoadMany RES_VIEW 700 702 703 13 701 558 704)
		(NormalEgo 0)
		(westDoor init: stopUpd:)
		(northDoor init: stopUpd:)
		(eastDoor init: stopUpd:)
		(cliffy init: stopUpd:)
		(cliffyHand init: stopUpd:)
		(energizeLight init: stopUpd:)
		(ambiLight1 init: setCycle: Forward)
		(ambiLight2 init: setCycle: Forward)
		(ambiLight3 init: setCycle: Forward)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						247 133
						282 133
						282 127
						202 127
						203 153
						304 153
						290 144
						257 144
					yourself:
				)
		)
		(super init:)
		(theMusic3
			number: 101
			owner: self
			flags: mNOPAUSE
			init:
			setLoop: -1
			play: 80
		)
		(curRoom setScript: sRogEnters)
	)
	
	(method (dispose)
		(theMusic3 dispose:)
		(super dispose: &rest)
	)
)

(instance sRogEnters of Script
	
	(method (doit)
		(if (and (< (wd40 x?) 160) (== state 2)) (self cue:))
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
			)
			(1
				(theMusic2 number: 103 setLoop: 1 play:)
				(eastDoor setCycle: EndLoop self)
			)
			(2
				(wd40
					setScale: Scaler 110 77 149 130
					init:
					setCycle: Forward
					setStep: 5 3
					setMotion: MoveTo -18 158
				)
			)
			(3
				(theMusic1 fade: 0 20 20 1)
				(NormalEgo 0)
				(ego
					x: 272
					y: 127
					setScale: Scaler 110 77 149 130
					setLoop: -1
					setLoop: 5
					init:
					setMotion: PolyPath 215 153 self
				)
			)
			(4
				(theMusic2 number: 103 setLoop: 1 play:)
				(eastDoor setCycle: BegLoop self)
			)
			(5 (messager say: N_QUICK_HIDE NULL NULL 0 self))
			(6 (= seconds 2))
			(7
				(ego
					view: 700
					setLoop: -1
					setLoop: 10
					cel: 0
					setCycle: EndLoop self
				)
			)
			(8
				(messager say: N_NOT_LIKE_THAT NULL NULL 0 self)
			)
			(9
				(ego setLoop: 9 cel: 0 setCycle: EndLoop self)
				(cliffy setLoop: 0 setMotion: MoveTo 14 170)
			)
			(10
				(NormalEgo 4)
				(ego loop: 0 setMotion: MoveTo 279 146 self)
			)
			(11
				(ego view: 0 setLoop: 8 setCel: 2 setCycle: 0)
				(= seconds 3)
			)
			(12
				(ego stopUpd:)
				(cliffy stopUpd:)
				(theGame handsOn:)
				(theIconBar disable: ICON_WALK)
				(pukoidN1 init:)
				(pukoidN2 init:)
				(pukoidN3 init:)
				(theMusic2 number: 103 setLoop: 1 play:)
				(eastDoor setCycle: EndLoop)
				(westDoor setCycle: EndLoop)
				(northDoor setCycle: EndLoop self)
			)
			(13
				(pukoidN1
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo 180 136
				)
				(pukoidN2
					setLoop: 12
					setCycle: Forward
					setMotion: MoveTo 188 129
				)
				(pukoidN3
					setLoop: 9
					setCycle: Forward
					setMotion: MoveTo 154 130
				)
				(pukoidE1
					setLoop: 0
					init:
					setCycle: Forward
					setMotion: MoveTo 207 134
				)
				(pukoidE2
					setLoop: 6
					init:
					setCycle: Forward
					setMotion: MoveTo 137 134
				)
				(pukoidW1
					setLoop: 0
					init:
					setCycle: Forward
					setMotion: MoveTo 158 138 self
				)
			)
			(14
				(= local0 1)
				(theMusic2 number: 103 setLoop: 1 play:)
				(eastDoor setCycle: BegLoop)
				(westDoor setCycle: BegLoop)
				(northDoor setCycle: BegLoop self)
			)
			(15
				(eastDoor stopUpd:)
				(westDoor addToPic:)
				(northDoor addToPic:)
				(= seconds 10)
			)
			(16
				(theGame handsOff:)
				(= local0 2)
				(pukoidW1 setScript: sRogPuked)
				(self dispose:)
			)
		)
	)
)

(instance sRogPuked of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(pukoidW1
					view: 678
					setCycle: Walk
					moveSpeed: 2
					init:
					setMotion: MoveTo 183 152 self
				)
			)
			(1
				(pukoidW1 setMotion: MoveTo 216 155 self)
			)
			(2
				(pukoidW1 setCycle: 0)
				(theMusic2 number: 519 setLoop: 1 play:)
				(thePuke init: setCycle: EndLoop self)
			)
			(3
				(thePuke dispose:)
				(ego view: 650 cel: 0 setCycle: EndLoop self)
			)
			(4
				(EgoDead deathGENETIXGUARD)
				(self dispose:)
			)
		)
	)
)

(instance sPukoidsTransformed of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: N_NOW_CLIFFY NULL NULL 0 self))
			(1
				(cliffyHand setCel: 1)
				(energizeLight setLoop: 5)
				(= cycles 1)
			)
			(2
				(eView1 init: stopUpd:)
				(= cycles 1)
			)
			(3
				(eView2 init: stopUpd:)
				(= cycles 1)
			)
			(4
				(eView3 init: stopUpd:)
				(= cycles 1)
			)
			(5
				(eView4 init: stopUpd:)
				(= cycles 1)
			)
			(6
				(eView5 init: stopUpd:)
				(= cycles 1)
			)
			(7
				(eView6 init: stopUpd:)
				(= cycles 1)
			)
			(8
				(eView7 init: stopUpd:)
				(eView8 init: stopUpd:)
				(= cycles 1)
			)
			(9
				(eView9 init: stopUpd:)
				(= cycles 1)
			)
			(10
				(eProp1 init: setCycle: EndLoop self)
			)
			(11
				(northDoor setCel: 0 setPri: 1 addToPic:)
				(pukoidN1 hide:)
				(pukoidN2 hide:)
				(pukoidN3 hide:)
				(pukoidE1 hide:)
				(pukoidE2 hide:)
				(head1 hide:)
				(head2 hide:)
				(head3 hide:)
				(head4 hide:)
				(head5 hide:)
				(if (== local0 1)
					(pukoidW1 hide:)
					(SolvePuzzle f1050PukoidsTransformed 20)
					(pukeBeamOut init:)
				else
					(pukoidW1 setScript: sRogPuked)
					(pukeBeamOut loop: 2 cel: 0 init:)
				)
				(= cycles 1)
			)
			(12
				(cast eachElementDo: #stopUpd)
				(= cycles 1)
			)
			(13
				(= theGOldCast cast)
				(= cast (EventHandler new:))
				(cast addToFront: pukeBeamOut)
				(theMusic2 number: 260 setLoop: 1 play:)
				(pukeBeamOut setCycle: BegLoop self)
			)
			(14
				(pukeBeamOut hide:)
				(= seconds 3)
			)
			(15
				(if (== local0 2)
					(self dispose:)
				else
					(theMusic2 number: 260 setLoop: 1 play:)
					(pukeBeamOut loop: 0 cel: 0 show: setCycle: EndLoop self)
				)
			)
			(16
				(theMusic1 number: 45 setLoop: -1 play:)
				(pukeBeamOut dispose:)
				(cast dispose:)
				(= cast theGOldCast)
				(pukoidN1
					view: 703
					loop: 0
					cel: 0
					x: 207
					y: 134
					show:
					addToPic:
				)
				(head1
					view: 703
					loop: 6
					cel: 0
					x: 210
					y: 98
					show:
					setCycle: Oscillate
				)
				(pukoidN2
					view: 703
					loop: 1
					cel: 0
					x: 180
					y: 136
					show:
					addToPic:
				)
				(head2
					view: 703
					loop: 7
					cel: 0
					x: 180
					y: 101
					show:
					setCycle: Oscillate
				)
				(pukoidN3
					view: 703
					loop: 2
					cel: 0
					x: 137
					y: 134
					show:
					addToPic:
				)
				(head3
					view: 703
					loop: 8
					cel: 0
					x: 136
					y: 100
					show:
					setCycle: Oscillate
				)
				(pukoidE1
					view: 703
					loop: 3
					cel: 0
					x: 154
					y: 130
					show:
					addToPic:
				)
				(head4
					view: 703
					loop: 9
					cel: 0
					x: 152
					y: 99
					show:
					setCycle: Oscillate
				)
				(pukoidE2
					view: 703
					loop: 4
					cel: 0
					x: 188
					y: 129
					show:
					addToPic:
				)
				(head5
					view: 703
					loop: 10
					cel: 0
					x: 188
					y: 99
					show:
					setCycle: Oscillate
				)
				(pukoidW1
					view: 703
					loop: 5
					cel: 0
					x: 161
					y: 136
					scaleX: 128
					scaleY: 128
					show:
					addToPic:
				)
				(head6 init: setCycle: Oscillate)
				(= seconds 4)
			)
			(17
				(messager say: N_CREW_RECOVERED NULL NULL 1 self)
			)
			(18
				(theMusic1 fade: 0 10 10 1)
				(= seconds 4)
			)
			(19
				(theMusic1 number: 28 setLoop: -1 play:)
				(= seconds 2)
			)
			(20
				(messager say: N_CREW_RECOVERED NULL NULL 2 self)
			)
			(21
				(theMusic2 number: 103 setLoop: 1 play:)
				(eastDoor setCycle: EndLoop)
				(NormalEgo 4)
				(ego setMotion: PolyPath 270 123 self)
			)
			(22
				(theMusic2 number: 103 setLoop: 1 play:)
				(eastDoor setCycle: BegLoop self)
			)
			(23
				(curRoom newRoom: 1040)
				(self dispose:)
			)
		)
	)
)

(instance sTooEarly of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: N_NOW_CLIFFY NULL NULL 0 self))
			(1
				(cliffyHand setCel: 1)
				(energizeLight setLoop: 5)
				(= cycles 2)
			)
			(2
				(energizeLight setLoop: 3 setCycle: Forward)
				(holdLight init:)
				(= cycles 1)
			)
			(3
				(pukoidW1 setScript: sRogPuked)
				(self dispose:)
			)
		)
	)
)

(instance westDoor of Prop
	(properties
		x 58
		y 91
		view 700
		loop 6
		signal ignrAct
	)
)

(instance northDoor of Prop
	(properties
		x 166
		y 94
		view 700
		loop 7
		priority 9
		signal (| ignrAct fixPriOn)
	)
)

(instance eastDoor of Prop
	(properties
		x 251
		y 91
		view 700
		loop 8
		signal ignrAct
	)
)

(instance wd40 of Actor
	(properties
		x 268
		y 127
		view 13
		signal ignrAct
	)
	
	(method (doit)
		(if (< (self x?) -10)
			(UnLoad RES_VIEW 13)
			(self dispose:)
		)
		(super doit: &rest)
	)
)

(instance cliffy of Actor
	(properties
		x 13
		y 144
		view 701
		priority 15
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(24
				(theGame handsOff:)
				(if (not local0)
					(curRoom setScript: sTooEarly)
				else
					(curRoom setScript: sPukoidsTransformed)
				)
			)
			(2
				(theGame handsOff:)
				(if (not local0)
					(curRoom setScript: sTooEarly)
				else
					(curRoom setScript: sPukoidsTransformed)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pukoidN1 of Actor
	(properties
		x 177
		y 121
		view 702
		loop 1
		signal (| ignrAct fixedLoop)
	)
	
	(method (init)
		(super init: &rest)
		(self setScale: Scaler 102 71 135 121)
	)
	
	(method (doit)
		(if
			(and
				(== (self mover?) 0)
				(== (self loop?) 1)
				(!= (self x?) 177)
			)
			(self setCycle: 0 setLoop: 2)
			(head1 init: setPri: (pukoidN1 priority?) setCycle: Oscillate)
		)
		(super doit: &rest)
	)
)

(instance pukoidN2 of Actor
	(properties
		x 180
		y 120
		view 702
		loop 12
		signal (| ignrAct fixedLoop)
	)
	
	(method (init)
		(super init: &rest)
		(self setScale: Scaler 102 82 130 121)
	)
	
	(method (doit)
		(if
			(and
				(== (self mover?) 0)
				(== (self loop?) 12)
				(!= (self x?) 180)
			)
			(self setCycle: 0 setLoop: 13)
			(head2 init: setPri: (pukoidN2 priority?) setCycle: Oscillate)
		)
		(super doit: &rest)
	)
)

(instance pukoidN3 of Actor
	(properties
		x 173
		y 121
		view 702
		loop 9
		signal (| ignrAct fixedLoop)
	)
	
	(method (init)
		(super init: &rest)
		(self setScale: Scaler 100 73 129 121)
	)
	
	(method (doit)
		(if
			(and
				(== (self mover?) 0)
				(== (self loop?) 9)
				(!= (self x?) 173)
			)
			(self setCycle: 0 setLoop: 10)
			(head3 init: setPri: (pukoidN3 priority?) setCycle: Oscillate)
		)
		(super doit: &rest)
	)
)

(instance pukoidE1 of Actor
	(properties
		x 253
		y 134
		view 702
		signal (| ignrAct fixedLoop)
	)
	
	(method (doit)
		(if
			(and
				(== (self mover?) 0)
				(== (self loop?) 0)
				(!= (self x?) 253)
			)
			(self setCycle: 0 setLoop: 4)
			(head4 init: setPri: (pukoidE1 priority?) setCycle: Oscillate)
		)
		(super doit: &rest)
	)
)

(instance pukoidE2 of Actor
	(properties
		x 263
		y 134
		view 702
		loop 6
		signal (| ignrAct fixedLoop)
	)
	
	(method (doit)
		(if
			(and
				(== (self mover?) 0)
				(== (self loop?) 6)
				(!= (self x?) 263)
			)
			(self setCycle: 0 setLoop: 7)
			(head5 init: setPri: (pukoidE2 priority?) setCycle: Oscillate)
		)
		(super doit: &rest)
	)
)

(instance pukoidW1 of Actor
	(properties
		x 57
		y 134
		view 678
		signal (| ignrAct fixedLoop)
		scaleSignal scalable
		scaleX 99
		scaleY 99
	)
	
	(method (init)
		(super init: &rest)
		(self setScale: Scaler 103 73 152 136)
	)
	
	(method (doit)
		(if (== (self mover?) 0) (self setCycle: 0))
		(super doit: &rest)
	)
)

(instance eProp1 of Prop
	(properties
		x 143
		y 47
		view 700
		loop 4
		signal ignrAct
	)
)

(instance head1 of Prop
	(properties
		x 177
		y 101
		view 702
		loop 3
		priority 10
		signal (| ignrAct fixPriOn)
		cycleSpeed 10
	)
)

(instance head2 of Prop
	(properties
		x 187
		y 97
		view 702
		loop 14
		priority 10
		signal (| ignrAct fixPriOn)
		cycleSpeed 11
	)
)

(instance head3 of Prop
	(properties
		x 152
		y 101
		view 702
		loop 11
		priority 10
		signal (| ignrAct fixPriOn)
		cycleSpeed 12
	)
)

(instance head4 of Prop
	(properties
		x 203
		y 98
		view 702
		loop 5
		priority 9
		signal (| ignrAct fixPriOn)
		cycleSpeed 12
	)
)

(instance head5 of Prop
	(properties
		x 132
		y 106
		view 702
		loop 8
		priority 10
		signal (| ignrAct fixPriOn)
		cycleSpeed 11
	)
)

(instance head6 of Prop
	(properties
		x 162
		y 98
		view 703
		loop 11
		priority 15
		signal (| ignrAct fixPriOn)
		cycleSpeed 10
	)
)

(instance thePuke of Prop
	(properties
		x 251
		y 118
		view 558
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance pukeBeamOut of Prop
	(properties
		x 163
		y 99
		view 704
		loop 1
		cel 7
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance ambiLight1 of Prop
	(properties
		x 219
		y 100
		view 700
	)
)

(instance ambiLight2 of Prop
	(properties
		x 209
		y 104
		view 700
		loop 1
	)
)

(instance ambiLight3 of Prop
	(properties
		x 309
		y 106
		view 700
		loop 2
	)
)

(instance energizeLight of Prop
	(properties
		x 99
		y 169
		view 701
		loop 3
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance holdLight of View
	(properties
		x 41
		y 159
		view 701
		loop 4
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance cliffyHand of View
	(properties
		x 99
		y 189
		view 701
		loop 2
		priority 15
		signal fixPriOn
	)
)

(instance eView1 of View
	(properties
		x 95
		y 1
		view 700
		loop 3
		signal ignrAct
	)
)

(instance eView2 of View
	(properties
		x 108
		y 10
		view 700
		loop 3
		cel 1
		signal ignrAct
	)
)

(instance eView3 of View
	(properties
		x 129
		y 12
		view 700
		loop 3
		cel 4
		signal ignrAct
	)
)

(instance eView4 of View
	(properties
		x 126
		y 24
		view 700
		loop 3
		cel 2
		signal ignrAct
	)
)

(instance eView5 of View
	(properties
		x 145
		y 27
		view 700
		loop 3
		cel 5
		signal ignrAct
	)
)

(instance eView6 of View
	(properties
		x 135
		y 32
		view 700
		loop 3
		cel 3
		signal ignrAct
	)
)

(instance eView7 of View
	(properties
		x 177
		y 32
		view 700
		loop 3
		cel 7
		signal ignrAct
	)
)

(instance eView8 of View
	(properties
		x 121
		y 36
		view 700
		loop 3
		cel 6
		signal ignrAct
	)
)

(instance eView9 of View
	(properties
		x 200
		y 41
		view 700
		loop 3
		cel 8
		signal ignrAct
	)
)

(instance theMusic3 of Sound
	(properties
		flags mNOPAUSE
	)
)
