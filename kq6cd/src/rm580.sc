;;; Sierra Script 1.0 - (do not remove this comment)
(script# 580)
(include sci.sh)
(use Main)
(use KQ6Room)
(use Kq6Procs)
(use Inset)
(use Conv)
(use Scaler)
(use Osc)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm580 0
)

(local
	local0
	theMessager
)
(instance rm580Messager of Kq6Messager
	(properties)
	
	(method (findTalker param1 &tmp temp0)
		(if
		(= temp0
			(switch param1
				(59 narrator)
				(60 narrator)
			))
			(return)
		else
			(super findTalker: param1)
		)
	)
)

(instance fireSound of Sound
	(properties
		number 560
		loop -1
	)
)

(instance rainSound of Sound
	(properties
		number 567
		loop -1
	)
)

(instance fx0 of Sound
	(properties)
)

(instance fx1 of Sound
	(properties)
)

(instance fx2 of Sound
	(properties)
)

(instance rm580 of KQ6Room
	(properties
		noun 3
		picture 580
		south 550
		west 560
		autoLoad 0
	)
	
	(method (init)
		(super init: &rest)
		(= theMessager messager)
		(= messager rm580Messager)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						89
						165
						42
						185
						0
						185
						0
						0
						319
						0
						319
						189
						249
						189
						208
						167
						261
						154
						255
						120
						213
						119
						166
						119
						126
						122
						56
						138
						49
						153
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 214 129 206 147 137 147 123 129 151 130 174 121 185 130
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 153 158 127 171 82 156 106 147 144 149
					yourself:
				)
		)
		(if (and (not (Btst 25)) (not (Btst 74)))
			(= local0 1)
		)
		(if
		(and (Btst 25) (not (Btst 14)) (not (Btst 74)))
			(Bset 14)
			(= local0 2)
		)
		(if local0
			(theIconBar
				enable:
				disable: 0 1 2 3 4 5 6
				height: -100
				activateHeight: -100
			)
			(Cursor showCursor: 0)
			(theMusic number: 569 loop: -1 flags: 1 play: hold: 10)
			(theGlobalSound stop:)
			(if (== local0 1)
				(druid init: setScale: Scaler 100 70 190 112 setPri: 14)
				(druid2 setScale: Scaler 100 70 190 112 init:)
			)
			(druid3 init: setScale: Scaler 100 70 190 112)
			(druid4
				init:
				setScale: Scaler 100 70 190 112
				ignoreActors: 1
				stopUpd:
			)
			(headDruid init: setScale: Scaler 100 70 190 112)
			(fire init: setCycle: RandCycle)
			(smoke init:)
			(fireSound play:)
			(rope init: stopUpd:)
			(cage init: setPri: 14 ignoreActors: 1 stopUpd:)
			(cageRope init: setPri: 14 ignoreActors: 1 stopUpd:)
		else
			(theMusic number: 570 loop: -1 flags: 1 play: hold: 10)
			(fire init: setLoop: 2 posn: 167 137 setCycle: RandCycle)
			(cage init: setPri: 12 ignoreActors: 1 addToPic:)
			(cageRope init: setPri: 12 ignoreActors: 1 addToPic:)
		)
		(bonfire init:)
		(circleOfStones init:)
		(trees init:)
		(ego init: setScale: Scaler 100 70 190 112)
		(if (== prevRoomNum 560)
			(ego posn: 9 187)
		else
			(ego posn: 164 212)
		)
		(theGame handsOff:)
		(self setScript: egoEnters)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 939)
		(Palette palSET_INTENSITY 0 255 100)
	)
	
	(method (newRoom n)
		(theMusic fade:)
		(= messager theMessager)
		(super newRoom: n)
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((== local0 2)
						(ego
							view: 5806
							posn: 148 235
							setLoop: 0
							setMotion: MoveTo 162 160 self
							setPri: 14
							setSpeed: 3
							setCycle: Walk
						)
					)
					((== prevRoomNum 560) (ego setMotion: MoveTo 162 (ego y?) self))
					(else (ego setMotion: MoveTo 162 170 self))
				)
			)
			(1
				(switch local0
					(0
						(theGame handsOn:)
						(self dispose:)
					)
					(1
						(if (== prevRoomNum 560)
							(ego setMotion: MoveTo 162 170 self)
						else
							(= ticks 1)
						)
					)
					(2
						(curRoom setScript: meetDruidsTwo)
					)
				)
			)
			(2
				(curRoom setScript: meetDruidsOne)
			)
		)
	)
)

(instance getEmbers of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 208 130 self)
			)
			(1 (ego setHeading: 225 self))
			(2
				(ego
					normal: 0
					view: 5803
					setLoop: 0
					cel: 0
					cycleSpeed: 10
					posn: 206 131
					setCycle: EndLoop self
				)
			)
			(3
				(ego reset: 5 posn: 211 129)
				(= cycles 2)
			)
			(4
				(if (not (Bset 140)) (theGame givePoints: 1))
				(cond 
					(
						(and
							(& (= temp1 ((inventory at: 11) state?)) $0004)
							(not (& temp1 $0001))
							(not (& temp1 $0002))
						)
						(messager say: 4 51 13 1 self)
					)
					(
						(and
							(& temp1 $0004)
							(or (& temp1 $0001) (& temp1 $0002))
						)
						(messager say: 4 51 14 1 self)
					)
					(else (messager say: 4 51 11 1 self))
				)
			)
			(5
				(ego reset: 2)
				(= temp0 (inventory at: 11))
				((ScriptID 0 4) setReal: temp0 0 5 0)
				(temp0
					setCursor: 990 0 9
					loop: 0
					cel: 10
					state: (| (temp0 state?) $000c)
				)
				((temp0 cursor?) loop: 0 cel: 9)
				(theGame setCursor: (temp0 cursor?) handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance meetDruidsOne of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 14)
				(messager say: 1 0 1 1 self)
			)
			(1 (messager say: 1 0 1 2 self))
			(2 (messager say: 1 0 1 3 self))
			(3
				(druid
					setPri: 14
					setCycle: Walk
					setSpeed: 0
					xStep: 6
					setMotion: MoveTo (- (ego x?) 25) (+ (ego y?) 3) self
					ignoreActors: 1
					illegalBits: 0
				)
				(druid2
					setCycle: Walk
					setSpeed: 0
					xStep: 6
					setMotion: MoveTo (+ (ego x?) 24) (+ (ego y?) 4) self
					ignoreActors: 1
					illegalBits: 0
				)
			)
			(4 0)
			(5
				(druid hide:)
				(druid2 hide:)
				(ego
					view: 554
					normal: 0
					setPri: 14
					setLoop: 0
					setCel: 0
					posn: (- (ego x?) 2) (ego y?)
					setCycle: EndLoop self
				)
			)
			(6
				(ego view: 5806 setLoop: 0 setCycle: Walk)
				(curRoom setScript: continueDruids)
			)
		)
	)
)

(instance meetDruidsTwo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(druid
					view: 553
					posn: 0 0
					init:
					setPri: 14
					ignoreActors: 1
					illegalBits: 0
					hide:
				)
				(druid2
					view: 553
					posn: 0 0
					init:
					ignoreActors: 1
					illegalBits: 0
					hide:
				)
				(Bset 14)
				(= cycles 2)
			)
			(1 (messager say: 1 0 1 1 self))
			(2 (messager say: 1 0 8 1 self))
			(3 (messager say: 1 0 8 2 self))
			(4
				(curRoom setScript: continueDruids)
			)
		)
	)
)

(instance continueDruids of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 1 0 1 4 self))
			(1 (messager say: 1 0 1 5 self))
			(2 (messager say: 1 0 1 6 self))
			(3
				(theMusic hold: 20)
				(cage signal: (| (cage signal?) $6000))
				(= cycles 2)
			)
			(4
				(ego setMotion: MoveTo 177 158 self)
			)
			(5
				(UnLoad 128 554)
				(ego
					view: 588
					setLoop: 0
					cel: 0
					setSpeed: 3
					setPri: (- (cage priority?) 1)
					posn: (- (ego x?) 18) (+ (ego y?) 2)
					setCycle: EndLoop self
				)
			)
			(6
				(ego view: 581 setLoop: 1 setPri: -1 setCycle: EndLoop self)
			)
			(7
				(cage
					view: 5807
					setLoop: 0
					cel: 0
					posn: 146 124
					setPri: 14
				)
				(cageRope view: 5807 setLoop: 1 cel: 0 setPri: 14)
				(ego view: 553 hide:)
				(UnLoad 128 580)
				(UnLoad 128 581)
				(druid
					show:
					posn: (- (ego x?) 12) (- (ego y?) 4)
					setLoop: -1
					setCycle: Walk
				)
				(druid2
					show:
					posn: (- (ego x?) 37) (- (ego y?) 10)
					setLoop: -1
					setCycle: Walk
				)
				(= cycles 2)
			)
			(8 (messager say: 1 0 1 7 self))
			(9
				(druid
					setMotion: MoveTo (druid x?) (+ (druid y?) 10) self
				)
				(druid2 setMotion: MoveTo 222 180 self)
			)
			(10
				(druid setMotion: MoveTo 85 178 self)
			)
			(11 0)
			(12
				(headDruid view: 587 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(13
				(headDruid view: 5808)
				(druid
					setLoop: 4
					cel: 3
					setLoop: -1
					setPri: (+ (cage priority?) 1)
				)
				(druid2 setLoop: 4 cel: 3 setLoop: -1)
				(druid3
					setPri: (- (rope priority?) 1)
					setLoop: -1
					setCycle: Walk
					setMotion: PolyPath 194 131 self
					ignoreActors: 1
				)
			)
			(14
				(druid5
					init:
					setLoop: -1
					setScale: Scaler 100 70 190 112
					setCycle: Walk
					setMotion: MoveTo 224 130 self
				)
			)
			(15
				(fx0 number: 563 loop: -1 play:)
				(druid3
					view: 5804
					setLoop: 0
					posn: 208 133
					setCycle: Forward
					setPri: -1
				)
				(rope dispose:)
				(druid5 dispose:)
				(cageRope setCel: 1)
				(cage posn: 147 124)
				(= cycles 5)
			)
			(16
				(cageRope setCel: 2)
				(cage posn: 152 117)
				(= cycles 5)
			)
			(17
				(cageRope setCel: 3)
				(cage posn: 166 107)
				(= cycles 5)
			)
			(18
				(cageRope dispose:)
				(cage
					view: 5801
					setPri: 14
					setCel: 0
					setLoop: 0
					cycleSpeed: 5
					posn: 170 54
					setCycle: EndLoop self
				)
			)
			(19
				(fx0 stop:)
				(UnLoad 128 5807)
				(cage cel: 0 setCycle: EndLoop self)
			)
			(20
				(druid3 setCycle: 0 setPri: 12 stopUpd:)
				(cage setLoop: 1 cel: 6 setCycle: EndLoop self)
			)
			(21
				(cage cel: 0 setCycle: EndLoop self)
			)
			(22
				(cage view: 5802 setLoop: 0 setCel: 0)
				(UnLoad 128 5801)
				(= cycles 2)
			)
			(23
				(messager say: 1 0 1 8 self)
			)
			(24
				(cage setPri: 12 stopUpd:)
				(smoke dispose:)
				(curRoom setInset: cageInset)
				(self dispose:)
			)
		)
	)
)

(instance inTheCage of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(theMusic hold: 30)
				(inEgo view: 583 setLoop: 0 cel: 1)
				(= ticks 12)
			)
			(2
				(inEgo stopUpd:)
				(messager say: 1 0 2 0 self)
			)
			(3 (= seconds 5))
			(4
				(theMusic hold: 0)
				(inEgo cel: 2)
				(= ticks 12)
			)
			(5
				(inEgo stopUpd:)
				(messager say: 1 0 3 0 self)
			)
			(6 (= seconds 3))
			(7
				(inEgo cel: 3)
				(= seconds 2)
			)
			(8
				(if (not global161)
					(messager say: 1 0 4 0 self)
				else
					(++ state)
					(messager say: 1 0 7 0 self)
				)
			)
			(9 (messager say: 1 0 7 0 self))
			(10
				(theMusic number: 561 loop: 1 play: self)
				(inEgo setLoop: 1 cel: 0 setCycle: EndLoop)
			)
			(11
				(= temp0 100)
				(while (>= temp0 0)
					(Palette palSET_INTENSITY 0 255 temp0)
					(= temp0 (- temp0 10))
				)
				(= cycles 1)
			)
			(12
				(cageInset dispose: 0)
				(= cycles 2)
			)
			(13
				(curRoom drawPic: 98)
				(cast eachElementDo: #dispose)
				(= cycles 2)
			)
			(14
				(Palette palSET_INTENSITY 0 255 100)
				(theIconBar height: 0 activateHeight: 0 enable: 6)
				(Cursor showCursor: 1)
				(if (== currentAct 5) (EgoDead 11) else (EgoDead 10))
			)
		)
	)
)

(instance makeRain of Script
	(properties)
	
	(method (changeState newState &tmp temp0 [temp1 50])
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(theMusic hold: 30)
				(inEgo cel: 1)
				(= ticks 12)
			)
			(2
				(inEgo stopUpd:)
				(messager say: 1 0 2 0 self)
			)
			(3 (= seconds 5))
			(4
				(theMusic hold: 0)
				(inEgo cel: 2)
				(= ticks 12)
			)
			(5
				(inEgo stopUpd:)
				(messager say: 1 0 3 0 self)
				(= seconds 5)
			)
			(6 (messager say: 1 0 6 0 self))
			(7
				(messager say: 1 0 9 1)
				(fx0 number: 562 loop: -1 play:)
				(inFlame show: setCycle: RandCycle)
				(= seconds 2)
			)
			(8
				(fx1 number: 568 loop: 1 play:)
				(inEgo
					view: 585
					setLoop: 1
					cel: 0
					cycleSpeed: 15
					setCycle: EndLoop self
				)
			)
			(9 (inEgo setCycle: EndLoop self))
			(10
				(inFlame dispose:)
				(fx0 stop:)
				(inEgo setCycle: EndLoop self)
			)
			(11
				(ego put: 5 580)
				(if (and (Btst 112) (not (ego has: 4)))
					(messager say: 1 0 15 1 self)
					(theGame givePoints: 1)
					(ego get: 4)
				else
					(messager say: 1 0 9 2 self)
				)
			)
			(12
				(inEgo view: 583 setLoop: 0 cel: 2 setCycle: 0)
				(UnLoad 128 585)
				(= cycles 2)
			)
			(13
				(messager say: 1 0 9 3 self)
			)
			(14 (= seconds 2))
			(15
				(fx0 number: 520 loop: -1 play:)
				(inEgo
					view: 582
					setLoop: 7
					cel: 0
					cycleSpeed: 15
					setCycle: Oscillate 2 self
				)
			)
			(16
				(inEgo setCycle: 0 stopUpd:)
				(messager say: 1 0 9 4 self)
			)
			(17 (= seconds 2))
			(18
				(inEgo setCycle: Oscillate 2 self)
			)
			(19
				(inEgo setCycle: 0 stopUpd:)
				(messager say: 1 0 9 5 self)
			)
			(20 (= seconds 2))
			(21
				(inEgo setCycle: Oscillate 2 self)
			)
			(22
				(inEgo setCycle: 0 stopUpd:)
				(messager say: 1 0 9 6 self)
			)
			(23
				(theGame givePoints: 2)
				(inEgo view: 5821 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(24
				(inEgo
					view: 582
					setLoop: 5
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(25 (inEgo setCycle: EndLoop self))
			(26 (inEgo setCycle: EndLoop self))
			(27 (inEgo setCycle: EndLoop self))
			(28
				(inEgo cel: 0 setCycle: EndLoop self)
			)
			(29
				(inEgo setLoop: 6 setCycle: EndLoop self)
			)
			(30
				(inEgo setCycle: 0 stopUpd:)
				(messager say: 1 0 9 7 self)
			)
			(31
				(messager say: 1 0 9 8 self)
			)
			(32
				(fx1 number: 565 loop: -1 play:)
				(inBillow show: setCycle: EndLoop self)
			)
			(33
				(inBillow setLoop: 1 cel: 0 setCycle: Forward)
				(= seconds 3)
			)
			(34
				(Palette palSET_FROM_RESOURCE 580)
				(= cycles 3)
			)
			(35
				(fx0 stop:)
				(fx1 stop:)
				(theMusic fade:)
				(fx2 number: 566 loop: 1 play:)
				(Palette palSET_FROM_RESOURCE 5801)
				(= seconds 2)
			)
			(36
				(fx2 play:)
				(Palette palSET_FROM_RESOURCE 580)
				(= cycles 3)
			)
			(37
				(Palette palSET_FROM_RESOURCE 5801)
				(= cycles 10)
			)
			(38
				(fx2 play:)
				(Palette palSET_FROM_RESOURCE 580)
				(= cycles 3)
			)
			(39
				(Palette palSET_FROM_RESOURCE 5801)
				(= cycles 30)
			)
			(40
				(messager say: 1 0 9 9 self)
			)
			(41
				(rainSound play:)
				(fireSound stop:)
				(= cycles 2)
			)
			(42
				(= temp0 100)
				(while (>= temp0 0)
					(Palette palSET_INTENSITY 0 255 temp0)
					(-- temp0)
				)
				(= cycles 1)
			)
			(43
				(cageInset dispose:)
				(druid3 dispose:)
				(druid4 dispose:)
				(smoke dispose:)
				(headDruid posn: (headDruid x?) (headDruid y?) 1000)
				(druid posn: (druid x?) (druid y?) 1000)
				(druid2 posn: (druid2 x?) (druid2 y?) 1000)
				(fire posn: (fire x?) (fire y?) 1000)
				(ego posn: (ego x?) (ego y?) 1000)
				(cage posn: (cage x?) (cage y?) 1000)
				(curRoom drawPic: 98)
				(= cycles 2)
			)
			(44
				(Palette palSET_INTENSITY 0 255 100)
				(= seconds 3)
			)
			(45 (fx2 play:) (= seconds 2))
			(46
				(rainSound play:)
				(messager say: 1 0 9 10 self)
				(Bset 74)
			)
			(47 (= seconds 3))
			(48
				(curRoom drawPic: 98 12)
				(Message msgGET 580 1 0 9 11 @temp1)
				(Display @temp1 dsCOORD 82 85 dsCOLOR 14 dsFONT 0)
				(= cycles 1)
			)
			(49
				(rainSound stop:)
				(= cycles 1)
			)
			(50
				(theMusic number: 570 loop: -1 play:)
				(= seconds 5)
			)
			(51
				(curRoom drawPic: 580 11)
				(cage
					view: 580
					loop: 0
					cel: 0
					ignoreActors: 1
					ignoreHorizon: 1
					posn: 147 124 0
					setPri: 12
					addToPic:
				)
				(cageRope
					view: 580
					init:
					loop: 1
					cel: 0
					ignoreHorizon: 1
					ignoreActors: 1
					posn: 170 54
					setPri: 12
					addToPic:
				)
				(fire posn: 167 137 0 setLoop: 2)
				(ego
					reset: 1
					setScale: Scaler 100 70 190 112
					posn: 131 135 0
				)
				(headDruid posn: (headDruid x?) (headDruid y?) 0)
				(druid posn: (druid x?) (druid y?) 0)
				(druid2 posn: (druid2 x?) (druid2 y?) 0)
				(= cycles 2)
			)
			(52
				(druidConv
					add: -1 1 0 10 1
					add: -1 1 0 10 2
					add: -1 1 0 10 3
					add: -1 1 0 10 4
					add: -1 1 0 10 5
					add: -1 1 0 10 6
					add: -1 1 0 10 7
					add: -1 1 0 10 8
					add: -1 1 0 10 9
					add: -1 1 0 10 10
					add: -1 1 0 10 11
					add: -1 1 0 10 12
					add: -1 1 0 10 13
					init: self
				)
			)
			(53
				(messager say: 1 0 10 14 self)
			)
			(54
				(messager say: 1 0 10 15 self)
			)
			(55
				(messager say: 1 0 10 16 self)
			)
			(56
				(messager say: 1 0 10 17 self)
			)
			(57
				(messager say: 1 0 10 18 self)
			)
			(58
				(messager say: 1 0 10 19 self)
			)
			(59
				(headDruid
					view: 584
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 50 (headDruid y?) self
				)
				(druid setMotion: PolyPath -20 189 self)
				(druid2 setMotion: PolyPath -20 189 self)
			)
			(60 0)
			(61 0)
			(62
				(headDruid dispose:)
				(druid dispose:)
				(druid2 dispose:)
				(theIconBar height: 0 activateHeight: 0 enable: 6)
				(Cursor showCursor: 1)
				(= global161 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance fire of Actor
	(properties
		x 170
		y 144
		view 589
		signal $4000
		cycleSpeed 17
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(bonfire doVerb: theVerb &rest)
	)
)

(instance smoke of Prop
	(properties
		x 169
		y 103
		view 589
		loop 1
		cel 4
		detailLevel 3
	)
	
	(method (doit)
		(super doit: &rest)
		(= cel (fire cel?))
	)
)

(instance cage of Actor
	(properties
		x 147
		y 124
		noun 9
		onMeCheck $0800
		view 580
	)
)

(instance cageRope of Actor
	(properties
		x 170
		y 54
		view 580
		loop 1
	)
)

(instance druid of Actor
	(properties
		x 82
		y 163
		view 553
	)
)

(instance druid2 of Actor
	(properties
		x 236
		y 163
		view 553
	)
)

(instance headDruid of Actor
	(properties
		x 90
		y 139
		view 5808
	)
)

(instance druid3 of Actor
	(properties
		x 227
		y 129
		view 553
		loop 1
	)
)

(instance druid4 of Prop
	(properties
		x 67
		y 146
		view 553
	)
)

(instance druid5 of Actor
	(properties
		x 240
		y 130
		view 553
	)
)

(instance rope of View
	(properties
		x 196
		y 135
		view 580
		cel 1
		priority 9
		signal $4010
	)
)

(instance bonfire of Feature
	(properties
		noun 4
		onMeCheck $0008
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 57 58 59 60 96)
			(messager say: noun 56 0)
		else
			(switch theVerb
				(51
					(if (& ((inventory at: 11) state?) $0008)
						(messager say: noun 51 16 1)
					else
						(theGame handsOff:)
						(curRoom setScript: getEmbers)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance circleOfStones of Feature
	(properties
		noun 7
		onMeCheck $0002
	)
)

(instance trees of Feature
	(properties
		noun 6
		onMeCheck $0004
	)
)

(instance cageInset of Inset
	(properties
		view 582
		x 85
		y 32
		priority 13
		noun 8
	)
	
	(method (init)
		(super init: &rest)
		(inEgo init: setPri: 14)
		(inFire init: setCycle: Forward cycleSpeed: 10 setPri: 14)
		(inFlame init: setPri: 13 hide:)
		(inBillow init: setPri: 14 hide:)
		(if (and (ego has: 19) (== global161 15))
			(curRoom setScript: makeRain)
		else
			(curRoom setScript: inTheCage)
		)
	)
	
	(method (dispose)
		(inEgo dispose:)
		(inFire dispose:)
		(inBillow dispose:)
		(addToPics eachElementDo: #dispose eachElementDo: #delete)
		(super dispose:)
	)
	
	(method (drawInset)
		(if (> picture 0)
			(if global169
				(DrawPic picture 15 (if anOverlay 0 else 1))
			else
				(DrawPic
					picture
					(if anOverlay 100 else style)
					(if anOverlay 0 else 1)
				)
			)
		)
		(if view
			(= insetView
				((inView new:)
					view: view
					loop: loop
					cel: cel
					x: x
					y: y
					setPri: priority
					ignoreActors: 1
					init:
					addToPic:
					yourself:
				)
			)
		)
	)
)

(instance inView of View
	(properties)
	
	(method (handleEvent)
		(return 0)
	)
)

(instance inEgo of Prop
	(properties
		x 162
		y 49
		noun 8
		view 583
	)
)

(instance inFire of Prop
	(properties
		x 149
		y 111
		noun 8
		view 582
		loop 8
		detailLevel 3
	)
)

(instance inFlame of Prop
	(properties
		x 179
		y 88
		noun 8
		view 585
	)
)

(instance inBillow of Prop
	(properties
		x 162
		y 49
		noun 8
		view 5821
		loop 2
		cycleSpeed 15
	)
)

(instance druidConv of Conversation
	(properties)
)
