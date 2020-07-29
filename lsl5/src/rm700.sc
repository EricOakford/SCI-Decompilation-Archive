;;; Sierra Script 1.0 - (do not remove this comment)
(script# 700)
(include game.sh)
(use Main)
(use LLRoom)
(use Door)
(use Intrface)
(use Talker)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm700 0
)

(local
	talkedToChangeGirl
	theGCycleCnt
)
(instance rm700 of LLRoom
	(properties
		lookStr {Tramp's Casino is a perfect example of the style of architecture exemplified by Frank Lloyd Wrong.}
		picture 700
		north 710
		east 760
		south 780
	)
	
	(method (init)
		(LoadMany SOUND 710 622 255 194 191 192)
		(ego init: normalize: 553)
		(switch prevRoomNum
			(750
				(HandsOn)
				(ego posn: 117 164 setHeading: 180 edgeHit: 0)
				(Bset fTrampCasinoDone)
			)
			(north
				(HandsOn)
				(ego posn: 117 164 setHeading: 180 edgeHit: 0)
				(theMusic fade: 80 10 10 0)
			)
			(south
				(self setScript: sFromSouth)
			)
			(east
				(ego y: 155)
			)
			(200
				(HandsOff)
				(self setScript: sFromLimo)
			)
			(else 
				(self setScript: sFromLimo)
			)
		)
		(super init:)
		(doorman init: approachVerbs: verbTalk)
		(if (!= prevRoomNum 750)
			(changeGirl init: approachVerbs: verbTalk)
		)
		(trampSign init:)
		(leftLight init:)
		(rightLight init:)
		(slotSign init:)
		(bird init:)
		(leftNip init:)
		(rightNip init:)
		(centerNip init:)
		(leftRoller init: setCycle: Forward setScript: sRoll)
		(middleRoller init: setCel: (Random 1 2) setCycle: Forward)
		(rightRoller init: setCel: 3 setCycle: Forward)
		(leftNeon init:)
		(rightNeon init:)
		(centerNeon init:)
		(dollars init:)
		(door init: approachVerbs: 3)
		(ass1 init:)
		(ass2 init:)
		(ass3 init:)
		(breasts1 init:)
		(breasts2 init:)
		(breasts3 init:)
		(girl1 init:)
		(girl2 init:)
		(girl3 init:)
		(burgerStand init:)
		(boardwalk init:)
		(beach init:)
		(trampSignF init:)
		(leftLightF init:)
		(rightLightF init:)
		(slotSignF init:)
		(leftNeonF init:)
		(rightNeonF init:)
		(centerNeonF init:)
		(dollarsF init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						221 176
						231 172
						319 172
						319 189
						298 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						98 163
						84 163
						84 161
						3 161
						3 169
						173 169
						197 176
						216 189
						0 189
						0 0
						319 0
						319 151
						253 151
						253 156
						228 161
						147 161
						143 163
						135 162
						121 156
						121 140
						115 140
						115 155
					yourself:
				)
		)
		(cond 
			((== prevRoomNum (curRoom north?))
				(theMusic fade: 80 10 10 0)
			)
			((!= prevRoomNum 200)
				(theMusic number: 710 setLoop: -1 flags: mNOPAUSE play: 80)
			)
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (and (Btst fIsVGA) (> (Abs (- gameTime theGCycleCnt)) 6))
			(= theGCycleCnt gameTime)
			(Palette PALCycle 33 48 -1)
			(Palette PALCycle 227 231 -1)
		)
		(cond 
			(script)
			((> (ego y?) 185)
				(HandsOff)
				(self setScript: sOcean)
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
		(if (== n 710)
			(theMusic fade: 127 10 10 0)
		else
			(theMusic fade:)
		)
		(if (== script sOcean)
			0
		else
			(super newRoom: n)
		)
	)
)

(instance sRoll of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(leftRoller x: 96 y: 48 setLoop: 7)
				(middleRoller hide:)
				(rightRoller hide:)
				(= seconds 5)
			)
			(2
				(leftRoller x: 99 y: 51 setLoop: 14)
				(middleRoller show:)
				(rightRoller show:)
				(self init:)
			)
		)
	)
)

(instance sFromSouth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 320 220 setMotion: PolyPath 220 180 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance sOcean of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 320 220 self)
			)
			(1 (curRoom newRoom: 780))
		)
	)
)

(instance sFromLimo of Script
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(== (soundFX prevSignal?) 10)
				(== (soundFX number?) 194)
			)
			(soundFX prevSignal: 5)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(limo init:)
				(ego hide:)
				(= ticks 150)
			)
			(1
				(soundFX number: 191 play:)
				(= ticks 50)
			)
			(2 (= ticks 60))
			(3
				(ego
					normalize: 553
					x: 107
					y: 177
					setLoop: 3
					show:
					setCel: 0
					setMotion: MoveTo 107 166 self
				)
			)
			(4
				(ego setLoop: -1 setHeading: 180 self)
			)
			(5 (= ticks 60))
			(6
				(soundFX number: 192 play:)
				(= ticks 100)
			)
			(7
				(theMusic number: 710 setLoop: -1 flags: mNOPAUSE play: 80)
				(soundFX number: 194 play:)
			)
			(8
				(limo setStep: 1 1 setMotion: MoveTo 87 (limo y?) self)
			)
			(9
				(limo setStep: 3 3 setMotion: MoveTo 65 (limo y?) self)
			)
			(10
				(limo setStep: 4 4 setMotion: MoveTo 50 (limo y?) self)
			)
			(11
				(limo setStep: 5 5 setMotion: MoveTo 35 (limo y?) self)
			)
			(12
				(limo setStep: 6 6 setMotion: MoveTo -95 (limo y?) self)
			)
			(13
				(limo dispose:)
				(ego normalize: 553)
				(HandsOn)
				(= cycles 1)
			)
			(14
				(self dispose:)
				(if
					(Print 700 0
						#button {Save} 1
						#button {Nah, Why Bother?} 0
						#title {AL says}
						#at -1 20
					)
					(theGame save:)
				)
			)
		)
	)
)

(instance sLimo of Script
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(== (soundFX prevSignal?) 10)
				(== (soundFX number?) 194)
			)
			(soundFX prevSignal: 5)
			(self cue:)
		)
	)
	
	(method (changeState newState &tmp [temp0 110])
		(switch (= state newState)
			(0
				(ego setHeading: 270)
				(doorman setLoop: 0 setCel: 0 setCycle: Oscillate 1 self)
			)
			(1
				(doorman setLoop: 1 setCel: 0)
				(soundFX number: 255 play: hold: 10)
				(limo
					posn: -100 178
					init:
					approachVerbs: 3
					setMotion: MoveTo 70 178 self
				)
			)
			(2
				(soundFX hold: -1)
				(ego setHeading: 90 self)
			)
			(3
				(switch silverDollars
					(0
						(TimePrint 700 1)
						(doorman setLoop: 1 setCel: 0 setCycle: EndLoop self)
					)
					(1
						(TimePrint 700 2)
						(doorman setLoop: 2 setCel: 0 setCycle: Oscillate 1 self)
					)
					(else 
						(Format @temp0 700 3 silverDollars)
						(TimePrint @temp0)
						(doorman setLoop: 2 setCel: 0 setCycle: Oscillate 1 self)
					)
				)
			)
			(4
				(switch silverDollars
					(0
						(Say Doorman 700 4 108 139 self)
					)
					(1
						(Say Doorman 700 5 108 139 self)
					)
					(else 
						(Say Doorman 700 6 108 139 self)
					)
				)
				(ego put: iSilverDollar 0)
				(= silverDollars 0)
			)
			(5
				(ego
					setMotion: PolyPath (limo approachX?) (limo approachY?) self
				)
			)
			(6 (ego setHeading: 180 self))
			(7 (= ticks 60))
			(8
				(soundFX number: 191 play:)
				(= ticks 50)
			)
			(9
				(ego
					setLoop: 2
					setCycle: 0
					setPri: (ego priority?)
					setMotion: MoveTo (ego x?) (+ (ego y?) 6) self
				)
			)
			(10 (ego x: 1000) (= ticks 60))
			(11
				(soundFX number: 192 play:)
				(= ticks 50)
			)
			(12 (= ticks 60))
			(13 (soundFX number: 194 play:))
			(14
				(limo setStep: 3 3 setMotion: MoveTo 65 (limo y?) self)
			)
			(15
				(limo setStep: 4 4 setMotion: MoveTo 50 (limo y?) self)
			)
			(16
				(limo setStep: 5 5 setMotion: MoveTo 35 (limo y?) self)
			)
			(17
				(limo setStep: 6 6 setMotion: MoveTo -95 (limo y?) self)
			)
			(18 (curRoom newRoom: 200))
		)
	)
)

(instance sGirlTalk of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 [temp2 30])
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_0d3b
			ldi      0
			sat      temp0
code_0ca4:
			pushi    0
			lat      temp0
			lt?     
			bnt      code_0cb0
			pprev   
			ldi      10000
			lt?     
code_0cb0:
			not     
			bnt      code_0cdb
			ldi      0
			sat      temp2
			pushi    5
			lea      @temp2
			push    
			pushi    6
			lofsa    {"Please enter a number less than 10000, ok?"}
			push    
			pushi    80
			lofsa    {Cheri}
			push    
			calle    GetInput,  10
			pushi    1
			lea      @temp2
			push    
			callk    ReadNumber,  2
			sat      temp0
			jmp      code_0ca4
code_0cdb:
			pushi    #has
			pushi    1
			pushi    17
			lag      ego
			send     6
			not     
			bnt      code_0cfe
			pushi    5
			lofsa    Cheri
			push    
			pushi    700
			pushi    7
			pushi    139
			pushSelf
			callb    Say,  10
			jmp      code_0db6
code_0cfe:
			lat      temp0
			sat      temp1
code_0d02:
			lst      temp0
			lat      temp1
			eq?     
			bnt      code_0d17
			pushi    2
			pushi    1
			pushi    6900
			callk    Random,  4
			sat      temp1
			jmp      code_0d02
code_0d17:
			pushi    4
			lea      @temp2
			push    
			pushi    700
			pushi    8
			lst      temp1
			callk    Format,  8
			pushi    4
			lofsa    Cheri
			push    
			lea      @temp2
			push    
			pushi    139
			pushSelf
			callb    Say,  8
			jmp      code_0db6
code_0d3b:
			dup     
			ldi      1
			eq?     
			bnt      code_0da0
			pushi    #has
			pushi    1
			pushi    17
			lag      ego
			send     6
			bnt      code_0d66
			pushi    6
			lofsa    Cheri
			push    
			pushi    700
			pushi    9
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_0db6
code_0d66:
			pushi    4
			lofsa    Cheri
			push    
			pushi    700
			pushi    10
			pushi    108
			callb    Say,  8
			pushi    #setLoop
			pushi    1
			pushi    1
			pushi    288
			pushi    1
			pushi    0
			pushi    150
			pushi    3
			class    Oscillate
			push    
			pushi    1
			pushSelf
			lofsa    changeGirl
			send     22
			pushi    #get
			pushi    1
			pushi    17
			lag      ego
			send     6
			ldi      10
			sag      silverDollars
			jmp      code_0db6
code_0da0:
			dup     
			ldi      2
			eq?     
			bnt      code_0db6
			pushi    #setLoop
			pushi    1
			pushi    0
			lofsa    changeGirl
			send     6
			pushi    #dispose
			pushi    0
			self     4
code_0db6:
			toss    
			ret     
		)
	)
)

(instance bird of Actor
	(properties
		description {the seagull}
		lookStr {A lonely seagull flies back and forth, searching for a likely target--you!}
		yStep 5
		view 700
		loop 8
		priority 14
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		illegalBits $0000
		xStep 7
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward cue:)
	)
	
	(method (cue &tmp temp0 temp1)
		(= temp0 (- (Random 0 520) 100))
		(= temp1 (- (Random 0 150) 50))
		(self setMotion: MoveTo temp0 temp1 self)
	)
)

(instance limo of Actor
	(properties
		x 93
		y 180
		description {the limousine}
		approachX 80
		approachY 175
		lookStr {Your limousine has returned. Or, perhaps, one suspiciously like it!}
		view 700
		signal (| ignrAct fixedLoop)
	)
)

(instance door of Door
	(properties
		x 118
		y 155
		description {the front door of Tramp's Casino}
		sightAngle 40
		approachX 120
		approachY 157
		lookStr {A small bronze plaque over the door reads, "Through these doors pass the greatest people in the world--OUR SUCKERS!"}
		view 251
		cycleSpeed 10
		entranceTo 710
		openSnd 622
		closeSnd 622
		moveToX 118
		moveToY 154
		enterType 0
		exitType 0
	)
)

(instance doorman of Prop
	(properties
		x 141
		y 162
		description {Brewster}
		sightAngle 40
		approachX 133
		approachY 165
		lookStr {Tramp's Casino thoughtfully provides a doorman to satisfy your every travel need--as long as all you need is for him to summon you a limousine!}
		view 703
		loop 1
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(self doVerb: verbTalk)
			)
			(verbTalk
				(Say Doorman 700 11 #dispose #caller self)
			)
			(verbZipper
				(Say Doorman 700 12 #dispose)
			)
			(verbUse
				(Say Doorman 700 13 #dispose)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(if
			(Print 700 14
				#button {No, thank you} 0
				#button {Yes, please} 1
				#title {Your Friendly Doorman}
			)
			(HandsOff)
			(Say ego 700 15)
			(SolvePuzzle 2 fDoormanCallsForLimo)
			(Say Doorman 700 16 #dispose)
			(curRoom setScript: sLimo)
		else
			(Say ego 700 17)
		)
	)
)

(instance changeGirl of Prop
	(properties
		x 96
		y 162
		description {the casino greeter}
		sightAngle 40
		approachX 103
		approachY 164
		lookStr {Tramp's Casino employs a beautiful young woman to greet all its distinguished visitors.}
		view 702
		signal ignrAct
	)
	
	(method (init)
		(super init:)
		(self approachVerbs: verbNone)
	)
	
	(method (doVerb theVerb theItem &tmp [temp0 32])
		(switch theVerb
			(verbDo
				(Say Cheri 700 18 #dispose)
			)
			(verbTalk
				(if (not talkedToChangeGirl)
					(Say Cheri 700 19 #dispose)
					(= talkedToChangeGirl TRUE)
					(self approachVerbs: verbTalk)
				else
					(SolvePuzzle 2 fTalkedToChangeGirl)
					(self setScript: sGirlTalk)
				)
			)
			(verbZipper
				(Say Cheri 700 20 #dispose)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance trampSign of Prop
	(properties
		x 120
		y 100
		description {the Tramp sign}
		lookStr {It says you're in front of Tramp's Casino.}
		view 700
		loop 1
		signal ignrAct
		cycleSpeed 3
		detailLevel 1
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init:)
	)
)

(instance slotSign of Prop
	(properties
		x 74
		y 38
		description {the slot machine sign}
		lookStr {You presume there must be some form of mechanized gambling within.}
		view 700
		loop 4
		signal ignrAct
		cycleSpeed 5
		detailLevel 2
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init:)
	)
)

(instance leftLight of Prop
	(properties
		x 67
		y 123
		description {the light}
		lookStr {Ain't neon grand!}
		view 700
		loop 2
		signal ignrAct
		cycleSpeed 2
		detailLevel 2
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init:)
	)
)

(instance rightLight of Prop
	(properties
		x 173
		y 124
		description {the light}
		lookStr {Ain't neon grand!}
		view 700
		loop 3
		cel 1
		signal ignrAct
		cycleSpeed 4
		detailLevel 2
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init:)
	)
)

(instance leftNip of Prop
	(properties
		x 43
		y 41
		description {the neon nipples}
		view 700
		loop 6
		signal ignrAct
		cycleSpeed 1
		detailLevel 3
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (Random 0 1)
					(TimePrint 700 21)
				else
					(TimePrint 700 22)
				)
			)
			(verbDo
				(if (Random 0 1)
					(TimePrint 700 23)
				else
					(TimePrint 700 24)
				)
			)
			(verbTalk
				(TimePrint 700 25)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance rightNip of Prop
	(properties
		x 220
		y 68
		description {the neon nipples}
		view 700
		loop 5
		cel 1
		signal ignrAct
		cycleSpeed 1
		detailLevel 3
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(leftNip doVerb: theVerb theItem &rest)
	)
)

(instance centerNip of Prop
	(properties
		x 159
		y 27
		description {the neon nipples}
		view 700
		loop 12
		cel 2
		signal ignrAct
		cycleSpeed 1
		detailLevel 3
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(leftNip doVerb: theVerb theItem &rest)
	)
)

(instance leftRoller of Prop
	(properties
		x 99
		y 51
		description {the slot machine sign}
		view 700
		loop 14
		signal ignrAct
		cycleSpeed 7
		detailLevel 3
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init:)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (Random 0 1)
					(TimePrint 700 26)
				else
					(TimePrint 700 27)
				)
			)
			(verbDo
				(TimePrint 700 28)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance middleRoller of Prop
	(properties
		x 110
		y 51
		description {the slot machine sign}
		view 700
		loop 14
		signal ignrAct
		detailLevel 3
	)
	
	(method (doVerb theVerb theItem)
		(leftRoller doVerb: theVerb theItem)
	)
)

(instance rightRoller of Prop
	(properties
		x 122
		y 51
		description {the slot machine sign}
		view 700
		loop 14
		cel 3
		signal ignrAct
		detailLevel 3
	)
	
	(method (doVerb theVerb theItem)
		(leftRoller doVerb: theVerb theItem)
	)
)

(instance leftNeon of Prop
	(properties
		x 52
		z -56
		description {the neon sign}
		lookStr {How you love gaudy things!}
		view 700
		loop 9
		signal ignrAct
		cycleSpeed 5
		detailLevel 5
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init:)
	)
)

(instance rightNeon of Prop
	(properties
		x 195
		z -70
		description {the neon sign}
		lookStr {How you love gaudy things!}
		view 700
		loop 10
		signal ignrAct
		cycleSpeed 4
		detailLevel 5
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init:)
	)
)

(instance centerNeon of Prop
	(properties
		x 125
		y 38
		description {the neon sign}
		lookStr {How you love gaudy things!}
		view 700
		loop 11
		signal ignrAct
		cycleSpeed 3
		detailLevel 4
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init:)
	)
)

(instance dollars of Prop
	(properties
		x 121
		y 115
		description {the dollar sign}
		lookStr {The implication here is that a fella could win some money inside this place!}
		view 700
		loop 13
		signal ignrAct
		cycleSpeed 9
		detailLevel 3
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init:)
	)
)

(instance burgerStand of Feature
	(properties
		x 280
		y 130
		nsTop 111
		nsLeft 241
		nsBottom 149
		nsRight 319
		description {the burger stand}
		sightAngle 40
		lookStr {How exciting! There's a restaurant along the Tramp Boardwalk that serves dead cow!}
	)
	
	(method (init)
		(super init:)
		(self approachVerbs: verbNone)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 700 29)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance boardwalk of Feature
	(properties
		x 280
		y 161
		nsTop 150
		nsLeft 241
		nsBottom 173
		nsRight 319
		description {the boardwalk}
		sightAngle 40
		lookStr {The boardwalk leads off to the east, stretching nearly as far as the eye can see.}
	)
)

(instance beach of Feature
	(properties
		x 183
		y 109
		nsTop 176
		nsLeft 234
		nsBottom 190
		nsRight 320
		description {the beach}
		sightAngle 40
		lookStr {That little strip of sand leads to a precipitous drop, then straight into the Atlantic! Care for a swim?}
	)
)

(instance breasts1 of Feature
	(properties
		x 42
		y 55
		nsTop 33
		nsLeft 31
		nsBottom 42
		nsRight 53
		description {the lights}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(leftNip doVerb: theVerb theItem)
	)
)

(instance breasts2 of Feature
	(properties
		x 156
		y 20
		nsTop 11
		nsLeft 148
		nsBottom 29
		nsRight 165
		description {the lights}
		sightAngle 40
	)
	
	(method (doVerb)
		(leftNip doVerb: &rest)
	)
)

(instance breasts3 of Feature
	(properties
		x 214
		y 74
		nsTop 60
		nsLeft 205
		nsBottom 72
		nsRight 224
		description {the rear}
		sightAngle 40
	)
	
	(method (doVerb)
		(leftNip doVerb: &rest)
	)
)

(instance ass1 of Feature
	(properties
		x 33
		y 62
		nsTop 55
		nsLeft 26
		nsBottom 70
		nsRight 43
		description {the rear}
		sightAngle 40
		lookStr {Tight butts drive you nuts!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 700 30)
			)
			(verbTalk
				(TimePrint 700 31)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance ass2 of Feature
	(properties
		x 120
		y 15
		nsLeft 108
		nsBottom 16
		nsRight 133
		sightAngle 40
	)
	
	(method (doVerb)
		(ass1 doVerb: &rest)
	)
)

(instance ass3 of Feature
	(properties
		x 213
		y 87
		nsTop 80
		nsLeft 204
		nsBottom 95
		nsRight 223
		description {the rear}
		sightAngle 40
	)
	
	(method (doVerb)
		(ass1 doVerb: &rest)
	)
)

(instance girl1 of Feature
	(properties
		x 34
		y 54
		nsTop 20
		nsLeft 8
		nsBottom 88
		nsRight 61
		description {the girl on the sign}
		sightAngle 40
		lookStr {Sure she's only made of plywood, but doesn't she have a great set of lights?}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 700 32)
			)
			(verbTalk
				(TimePrint 700 33)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance girl2 of Feature
	(properties
		x 138
		y 14
		nsLeft 92
		nsBottom 28
		nsRight 185
		description {the girl}
		sightAngle 40
		lookStr {She's in one of your favorite positions... for a sign!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 700 34)
			)
			(verbTalk
				(TimePrint 700 35)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance girl3 of Feature
	(properties
		x 206
		y 72
		nsTop 39
		nsLeft 180
		nsBottom 105
		nsRight 232
		description {the girl}
		sightAngle 40
		lookStr {And you were so afraid you were wasting your money buying this game!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 700 36)
			)
			(verbTalk
				(TimePrint 700 37)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance Cheri of Talker
	(properties
		nsTop 15
		nsLeft 12
		view 1702
		loop 3
		viewInPrint 1
	)
	
	(method (init)
		(= bust girlBust)
		(= eyes girlEyes)
		(= mouth girlMouth)
		(super init: &rest)
	)
)

(instance girlEyes of Prop
	(properties
		nsTop 8
		nsLeft 8
		view 1702
		loop 2
		cycleSpeed 30
	)
)

(instance girlBust of Prop
	(properties
		view 1702
		loop 1
	)
)

(instance girlMouth of Prop
	(properties
		nsTop 33
		nsLeft 30
		view 1702
		cycleSpeed 8
	)
)

(instance Doorman of Talker
	(properties
		nsTop 15
		nsLeft 50
		view 1703
		loop 3
		viewInPrint TRUE
	)
	
	(method (init)
		(= bust manBust)
		(= eyes manEyes)
		(= mouth manMouth)
		(super init: &rest)
	)
)

(instance manBust of Prop
	(properties
		view 1703
		loop 1
	)
)

(instance manEyes of Prop
	(properties
		nsTop 8
		nsLeft 8
		view 1703
		loop 2
		cycleSpeed 30
	)
)

(instance manMouth of Prop
	(properties
		nsTop 31
		nsLeft 11
		view 1703
		cycleSpeed 8
	)
)

(instance trampSignF of Feature
	(properties
		x 121
		y 152
		z 69
		nsTop 70
		nsLeft 88
		nsBottom 97
		nsRight 154
		description {the Tramp sign}
		sightAngle 40
		lookStr {It says you're in front of Tramp's Casino.}
	)
)

(instance leftLightF of Feature
	(properties
		x 68
		y 157
		z 58
		nsTop 82
		nsLeft 62
		nsBottom 117
		nsRight 75
		description {the light}
		sightAngle 40
		lookStr {Ain't neon grand!}
	)
)

(instance rightLightF of Feature
	(properties
		x 172
		y 158
		z 59
		nsTop 81
		nsLeft 162
		nsBottom 118
		nsRight 182
		description {the light}
		sightAngle 40
		lookStr {Ain't neon grand!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance slotSignF of Feature
	(properties
		x 62
		y 160
		z 103
		nsTop 37
		nsLeft 53
		nsBottom 78
		nsRight 71
		description {the slot sign}
		sightAngle 40
		lookStr {You presume there must be some form of mechanized gambling within.}
	)
)

(instance leftNeonF of Feature
	(properties
		x 28
		y 157
		z 143
		nsTop 1
		nsLeft 4
		nsBottom 28
		nsRight 53
		description {the neon sign}
		sightAngle 40
		lookStr {How you love gaudy things!}
	)
)

(instance rightNeonF of Feature
	(properties
		x 206
		y 157
		z 137
		nsLeft 185
		nsBottom 41
		nsRight 228
		description {the neon sign}
		sightAngle 40
		lookStr {How you love gaudy things!}
	)
)

(instance centerNeonF of Feature
	(properties
		x 124
		y 158
		z 126
		nsTop 25
		nsLeft 110
		nsBottom 40
		nsRight 139
		description {the neon sign}
		sightAngle 40
		lookStr {How you love gaudy things!}
	)
)

(instance dollarsF of Feature
	(properties
		x 124
		y 159
		z 50
		nsTop 102
		nsLeft 90
		nsBottom 116
		nsRight 158
		description {the dollar sign}
		sightAngle 40
		lookStr {The implication here is that a fella could win some money inside this place!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance soundFX of Sound
	(properties
		flags mNOPAUSE
	)
)
