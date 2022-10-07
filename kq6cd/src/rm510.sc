;;; Sierra Script 1.0 - (do not remove this comment)
(script# 510)
(include sci.sh)
(use Main)
(use KQ6Room)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm510 0
)

(local
	local0
	genieScript
	genieLoop
	theCel =  -1
	[local4 16] = [0 189 0 0 187 0 187 135 211 155 216 164 209 172 121 189]
	[local20 18] = [155 189 248 182 257 174 254 165 234 152 202 136 202 0 319 0 319 189]
	[local38 24] = [0 189 0 0 50 0 82 85 41 85 68 123 159 123 176 130 209 154 218 162 211 172 120 189]
	[local62 26] = [163 189 248 177 255 167 189 131 189 123 241 123 241 105 197 97 147 86 100 86 71 0 319 0 319 189]
	[local88 46] = [0 189 0 0 319 0 319 189 165 189 177 185 232 177 252 175 253 165 229 147 180 125 243 125 243 105 210 100 145 85 37 85 67 123 160 123 179 130 216 158 216 167 205 173 125 189]
)
(instance rm510 of KQ6Room
	(properties
		noun 3
		picture 510
		horizon 0
		north 540
		south 520
	)
	
	(method (init)
		(if (Btst 8)
			(self
				addObstacle:
					(poly1After type: 2 points: @local38 size: 12 yourself:)
					(poly2After type: 2 points: @local62 size: 13 yourself:)
			)
		else
			(self
				addObstacle:
					(poly1Before type: 2 points: @local4 size: 8 yourself:)
					(poly2Before type: 2 points: @local20 size: 9 yourself:)
			)
		)
		(super init: &rest)
		(if (== prevRoomNum north)
			(ego init: reset: 2 posn: 102 99)
			(theGlobalSound number: 917 flags: 1 loop: -1 play:)
		else
			(ego init: reset: 3 posn: 146 187)
		)
		(if (== ((inventory at: 2) owner?) curRoomNum)
			(brick init:)
		)
		(archer init: ignoreActors: 1 setPri: 10)
		(if (Btst 8)
			(archer setLoop: 6 ignoreActors: 1 addToPic:)
		)
		(statue2 init:)
		(wall init:)
		(roseFeature init:)
		(trees init:)
		(garden init:)
		(gazebo init:)
		(path init:)
		(rocks init:)
		(ego
			setScale: Scaler 100 67 190 86
			actions: egoUseShieldCode
		)
		(roses init: setPri: 5)
		(if (Btst 9)
			(roses
				setLoop: 0
				cel: 7
				noun: 16
				ignoreActors: 1
				addToPic:
			)
		else
			(roses setLoop: 2 cel: 0)
		)
		(if (not (Btst 98))
			(Bset 98)
			(genie init: setScript: genieSummoning)
			(glint1 init: hide:)
			(glint2 init: hide:)
			(genieSong play:)
		else
			(theGame handsOn:)
		)
	)
	
	(method (doit)
		(cond 
			(
				(and
					(not (Btst 8))
					(== (ego onControl: 1) 2)
					(!= script walkPastArcher)
					(!= script walkPastArcherGenie)
					(not (== prevRoomNum north))
				)
				(theGame handsOff:)
				(if (cast contains: genie)
					(genie setScript: 0)
					(self setScript: walkPastArcherGenie)
				else
					(self setScript: walkPastArcher 0 0)
				)
			)
			(script 0)
			(
			(and (== (ego onControl: 1) 128) (== (roses loop?) 2)) (theGame handsOff:) (self setScript: rosesClose))
			(
			(and (== (ego onControl: 1) 128) (== (roses loop?) 0)) (curRoom newRoom: north))
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 1)
				(if (cast contains: genie)
					(theConv
						add: -1 noun theVerb
						add: -1 noun theVerb 9
						init:
					)
					(return 1)
				else
					(super doVerb: theVerb &rest)
				)
			else
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (newRoom n)
		(if
		(and (== n south) (not script) (cast contains: genie))
			(theGame handsOff:)
			(genie setScript: 0)
			(self setScript: genieMadScript 0 1)
		else
			(super newRoom: n)
		)
	)
)

(instance glint1 of Prop
	(properties
		view 902
		signal $4000
	)
)

(instance glint2 of Prop
	(properties
		view 902
		signal $4000
	)
)

(instance brick of View
	(properties
		x 254
		y 156
		noun 5
		view 510
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(ego setScript: getBrick)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance genie of Actor
	(properties
		x 214
		y 101
		noun 7
		view 514
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(67
				(theGame handsOff:)
				(genie setScript: 0)
				(curRoom setScript: genieEatsPeppermint)
			)
			(2
				(if local0
					(theGame handsOff:)
					(curRoom setScript: interruptGenieScript 0 2)
				else
					(theGame handsOff:)
					(curRoom setScript: interruptGenieScript 0 1)
					(++ local0)
				)
			)
			(5
				(super doVerb: theVerb &rest)
			)
			(1
				(super doVerb: theVerb &rest)
			)
			(else 
				(theGame handsOff:)
				(curRoom setScript: interruptGenieScript 0 theVerb)
			)
		)
	)
)

(instance archer of Prop
	(properties
		x 238
		y 137
		z 79
		noun 10
		view 510
		loop 1
	)
	
	(method (doit)
		(super doit: &rest)
		(if (and (!= loop 6) (not (curRoom script?)))
			(self
				cel: (- 8 (/ (- (GetAngle x y (ego x?) (ego y?)) 105) 17))
			)
			(if (!= cel theCel) (creak play:) (= theCel cel))
		)
	)
	
	(method (doVerb theVerb)
		(cond 
			((OneOf theVerb 1 5 2)
				(if (Btst 8)
					(messager say: noun theVerb 13 0)
				else
					(messager say: noun theVerb 14 0)
				)
			)
			((== theVerb 17)
				(theGame handsOff:)
				(curRoom setScript: walkPastArcher 0 1)
			)
			((== theVerb 39) (messager say: noun theVerb 0 0))
			((Btst 8) (messager say: noun 0 13 0))
			(else (messager say: noun 0 14 0))
		)
	)
)

(instance fx of Sound
	(properties)
)

(instance genieSong of Sound
	(properties
		flags $0001
		number 510
		loop -1
	)
)

(instance creak of Sound
	(properties
		number 513
	)
)

(instance statue2 of Feature
	(properties
		noun 11
		onMeCheck $0004
	)
)

(instance wall of Feature
	(properties
		noun 4
		onMeCheck $0010
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 1 5 2)
			(super doVerb: theVerb)
		else
			(messager say: 4 0 0 1)
		)
	)
)

(instance path of Feature
	(properties
		noun 6
		onMeCheck $0022
	)
	
	(method (doVerb theVerb)
		(if
		(or (== theVerb 1) (== theVerb 5) (== theVerb 2))
			(super doVerb: theVerb)
		else
			(messager say: 6 0 0 1)
		)
	)
)

(instance roses of Prop
	(properties
		x 85
		y 80
		noun 15
		view 513
		priority 14
	)
	
	(method (doVerb theVerb)
		(cond 
			((OneOf theVerb 1 2 5)
				(cond 
					(
						(not
							(cond 
								((== (roses loop?) 0))
								((== (roses loop?) 2) (== (roses cel?) 0))
							)
						)
						(messager say: noun theVerb 0)
					)
					((== noun 16) (messager say: noun theVerb 0))
					(else (gazebo doVerb: theVerb &rest))
				)
			)
			(
			(OneOf theVerb 28 39 8 30 25 12 94 70 71 26 20 34)
				(cond 
					(
						(not
							(cond 
								((== (roses loop?) 0))
								((== (roses loop?) 2) (== (roses cel?) 0))
							)
						)
						(messager say: noun theVerb 0 0)
					)
					((== noun 16) (messager say: noun 0 0))
					(else (gazebo doVerb: theVerb &rest))
				)
			)
			((== theVerb 16)
				(cond 
					(
						(not
							(cond 
								((== (roses loop?) 0))
								((== (roses loop?) 2) (== (roses cel?) 0))
							)
						)
						(theGame handsOff:)
						(curRoom setScript: cutEmBaby)
					)
					((== noun 16) (messager say: noun 0 0))
					(else (gazebo doVerb: theVerb &rest))
				)
			)
			(
				(not
					(cond 
						((== (roses loop?) 0))
						((== (roses loop?) 2) (== (roses cel?) 0))
					)
				)
				(messager say: noun 0 0)
			)
			((== noun 16) (messager say: noun 0 0))
			(else (gazebo doVerb: theVerb &rest))
		)
	)
)

(instance gazebo of Feature
	(properties
		noun 13
		onMeCheck $0800
	)
)

(instance roseFeature of Feature
	(properties
		noun 9
		onMeCheck $0040
		approachX 51
		approachY 89
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 5)
				(cond 
					((ego has: 38) (messager say: noun theVerb 20))
					((Btst 8) (theGame handsOff:) (curRoom setScript: pickRoseScript))
					(else (messager say: noun theVerb 14))
				)
			)
			((== theVerb 16)
				(if
					(or
						(== (roses loop?) 0)
						(and (== (roses loop?) 2) (== (roses cel?) 0))
					)
					(messager say: noun theVerb 17)
				else
					(messager say: noun theVerb 18)
				)
			)
			((OneOf theVerb 1 71 2) (super doVerb: theVerb &rest))
			((not (Btst 8)) (messager say: noun 0 14))
			(
				(and
					(== (roses loop?) 2)
					(== (roses cel?) (roses lastCel:))
				)
				(messager say: noun 0 18)
			)
			(else (messager say: noun 0 17))
		)
	)
	
	(method (onMe)
		(if (Btst 8) (self approachVerbs: 1 5))
		(super onMe: &rest)
	)
)

(instance garden of Feature
	(properties
		noun 14
		onMeCheck $0100
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if (Btst 8)
					(messager say: noun theVerb 13)
				else
					(messager say: noun theVerb 14)
				)
			)
			(1
				(if (cast contains: genie)
					(messager say: noun theVerb 9)
				else
					(messager say: noun theVerb 8)
				)
			)
			(2
				(super doVerb: theVerb &rest)
			)
			(71
				(messager say: noun theVerb 0)
			)
			(else 
				(if (Btst 8)
					(messager say: noun 0 13)
				else
					(messager say: noun 0 14)
				)
			)
		)
	)
)

(instance trees of Feature
	(properties
		noun 12
		onMeCheck $0200
	)
)

(instance rocks of Feature
	(properties
		noun 2
		onMeCheck $0400
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 1) (messager say: noun theVerb 2 0 0 0))
			((OneOf theVerb 1 2 5) (messager say: noun theVerb 0 0 0 0))
			(else (messager say: noun 0 0 0 0 0))
		)
	)
)

(instance interruptGenieScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= genieScript (genie script?))
				(genie script: 0)
				(= seconds 2)
			)
			(1
				(= genieLoop (genie loop?))
				(genie
					x: (- (genie x?) 9)
					y: (+ (genie y?) 12)
					setLoop: 6
				)
				(= ticks 1)
			)
			(2
				(switch register
					(1
						(messager say: 7 2 15 0 self)
					)
					(2
						(messager say: 7 2 16 0 self)
					)
					(3 (messager say: 1 0 3 0 self))
					(4 (messager say: 1 0 4 0 self))
					(5 (messager say: 1 0 5 1 self))
					(6 (messager say: 1 0 2 0 self))
					(else 
						(if (== register 43)
							(messager say: 7 register 0 0 self)
						else
							(messager say: 7 0 0 0 self)
						)
					)
				)
			)
			(3
				(glint1
					show:
					setPri: (+ (genie priority?) 1)
					posn: (- (genie x?) 3) (- (genie y?) 17)
				)
				(glint2
					show:
					setPri: (+ (genie priority?) 1)
					posn: (- (genie x?) 1) (- (genie y?) 17)
				)
				(= ticks 1)
			)
			(4
				(glint1 setCycle: EndLoop self)
				(glint2 setCycle: EndLoop self)
			)
			(5 0)
			(6
				(glint1 setCycle: BegLoop self)
				(glint2 setCycle: BegLoop self)
			)
			(7 0)
			(8
				(glint1 hide:)
				(glint2 hide:)
				(= seconds 2)
			)
			(9
				(if (cast contains: genie)
					(genie
						loop: genieLoop
						x: (+ (genie x?) 9)
						y: (- (genie y?) 12)
						script: genieScript
					)
				)
				(= genieScript 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance genieMadScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(genie
					x: (- (genie x?) 9)
					y: (+ (genie y?) 12)
					setLoop: 6
				)
				(= ticks 1)
			)
			(1 (messager say: 1 0 5 1 self))
			(2
				(glint1
					show:
					setPri: (+ (genie priority?) 1)
					posn: (- (genie x?) 3) (- (genie y?) 17)
				)
				(glint2
					show:
					setPri: (+ (genie priority?) 1)
					posn: (- (genie x?) 1) (- (genie y?) 17)
				)
				(= ticks 1)
			)
			(3
				(glint1 setCycle: EndLoop self)
				(glint2 setCycle: EndLoop self)
			)
			(4 0)
			(5
				(glint1 setCycle: BegLoop self)
				(glint2 setCycle: BegLoop self)
			)
			(6 0)
			(7
				(glint1 hide:)
				(glint2 hide:)
				(= seconds 1)
			)
			(8
				(self setScript: genieOuttaHere self)
			)
			(9 (messager say: 1 0 5 2 self))
			(10
				(if register
					(curRoom newRoom: (curRoom south?))
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
		)
	)
)

(instance getBrick of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 251 160 self)
			)
			(1 (= cycles 1))
			(2
				(ego
					view: 511
					setLoop: 0
					cel: 0
					cycleSpeed: 10
					setHeading: 0
					normal: 0
					setCycle: CycleTo 2 1 self
				)
			)
			(3
				(brick dispose:)
				(theGame givePoints: 1)
				(ego get: 2 setCycle: EndLoop self)
			)
			(4
				(ego setHeading: 180 reset:)
				(= seconds 2)
			)
			(5
				(theGame handsOn:)
				(messager say: 5 5)
				(self dispose:)
			)
		)
	)
)

(instance walkPastArcher of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				register
				(< (ego y?) 160)
				(== state 1)
				(!= (ego view?) 511)
			)
			(ego normal: 0 view: 511 setLoop: 4)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register
					(messager say: 2 17 8 1 self)
				else
					(= cycles 1)
				)
			)
			(1
				(if register
					(if (< (ego y?) 160)
						(ego normal: 0 view: 511 setLoop: 4)
					)
					(ego setMotion: PolyPath 205 150 self)
				else
					(= cycles 1)
				)
			)
			(2
				(ego view: 511 normal: 0 cycleSpeed: 10 cel: 0)
				(if register
					(ego setLoop: 2)
				else
					(ego
						setPri: 11
						posn: (+ (ego x?) 2) (+ (ego y?) 4)
						setLoop: 1
					)
				)
				(= cycles 1)
			)
			(3
				(if register (= seconds 2) else (= cycles 1))
			)
			(4
				(fx number: 511 loop: 1 play: self)
				(archer setCel: 0 cycleSpeed: 10 setLoop: 2 setCycle: EndLoop)
			)
			(5
				(if register
					(fx number: 514 loop: 1 play:)
				else
					(fx number: 512 loop: 1 play:)
				)
				(ego setCycle: EndLoop self)
			)
			(6
				(if register (ego reset: 7 setCycle: Walk))
				(= cycles 1)
			)
			(7
				(if register
					(messager say: 2 17 8 3 self)
				else
					(messager say: 1 0 7 4 self)
				)
			)
			(8
				(if register
					(theGame givePoints: 3)
					(Bset 8)
					(ego put: 43 510)
					(archer view: 510 setLoop: 6 setCel: 0)
					(= cycles 1)
					((curRoom obstacles?)
						delete: poly1Before
						delete: poly2Before
						add:
							(poly1After type: 2 points: @local38 size: 12 yourself:)
							(poly2After type: 2 points: @local62 size: 13 yourself:)
					)
				else
					(theGame handsOn:)
					(EgoDead 1)
				)
			)
			(9
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance walkPastArcherGenie of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 163)
				(genie
					x: (- (genie x?) 9)
					y: (+ (genie y?) 12)
					setLoop: 6
				)
				(ego
					view: 511
					normal: 0
					cycleSpeed: 10
					cel: 0
					setPri: 11
					posn: (+ (ego x?) 2) (+ (ego y?) 4)
					setLoop: 1
				)
				(= cycles 1)
			)
			(1
				(fx number: 511 loop: 1 play: self)
				(archer setCel: 0 cycleSpeed: 10 setLoop: 2 setCycle: EndLoop)
			)
			(2
				(fx number: 512 loop: 1 play:)
				(ego setCycle: EndLoop self)
			)
			(3
				(genie cycleSpeed: 15 setLoop: 7 setCycle: EndLoop self)
			)
			(4
				(genie setLoop: 8 cel: 0 setCycle: EndLoop self)
			)
			(5 (= cycles 2))
			(6 (genie setCycle: BegLoop self))
			(7 (= cycles 2))
			(8 (genie setCycle: EndLoop self))
			(9 (= cycles 2))
			(10 (genie setCycle: BegLoop self))
			(11 (= cycles 2))
			(12 (genie setCycle: EndLoop self))
			(13 (= cycles 2))
			(14 (genie setCycle: BegLoop self))
			(15 (= cycles 2))
			(16
				(glint1
					show:
					setPri: (+ (genie priority?) 1)
					posn: (+ (genie x?) 1) (- (genie y?) 41)
					setCycle: EndLoop self
				)
				(glint2
					show:
					setPri: (+ (genie priority?) 1)
					posn: (- (genie x?) 1) (- (genie y?) 41)
					setCycle: EndLoop self
				)
			)
			(17 0)
			(18
				(glint1 setCycle: BegLoop self)
				(glint2 setCycle: BegLoop self)
			)
			(19 0)
			(20
				(glint1 hide:)
				(glint2 hide:)
				(= cycles 2)
			)
			(21
				(messager say: 1 0 6 5 self)
			)
			(22
				(theGame handsOn:)
				(EgoDead 1)
			)
		)
	)
)

(instance genieEatsPeppermint of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(genie setLoop: 7 setCel: 0 setCycle: EndLoop self)
			)
			(1
				(genie setLoop: 8 setCel: 0)
				(= cycles 2)
			)
			(2
				(theConv add: -1 7 67 0 1 init: self)
			)
			(3
				(glint1
					show:
					setPri: (+ (genie priority?) 1)
					posn: (- (genie x?) 1) (- (genie y?) 41)
				)
				(glint2
					show:
					setPri: (+ (genie priority?) 1)
					posn: (+ (genie x?) 1) (- (genie y?) 41)
				)
				(= ticks 1)
			)
			(4
				(glint1 setCycle: EndLoop self)
				(glint2 setCycle: EndLoop self)
			)
			(5 0)
			(6
				(glint1 setCycle: BegLoop self)
				(glint2 setCycle: BegLoop self)
			)
			(7 0)
			(8
				(glint1 hide:)
				(glint2 hide:)
				(= seconds 2)
			)
			(9
				(theConv
					add: -1 7 67 0 2
					add: -1 7 67 0 3
					add: -1 7 67 0 4
					init: self
				)
			)
			(10
				(fx number: 943 loop: 1 play:)
				(genie cycleSpeed: 3 setLoop: 5 setCycle: EndLoop self)
			)
			(11
				(genie
					posn: (+ (ego x?) 33) (- (ego y?) 5)
					setCycle: BegLoop self
				)
				(fx number: 943 loop: 1 play:)
			)
			(12 (Face ego genie self))
			(13
				(ego normal: 0 view: 514 setLoop: 11 cel: 0)
				(= cycles 2)
			)
			(14
				(theConv add: -1 7 67 0 5 add: -1 7 67 0 6 init: self)
			)
			(15
				(genie
					cycleSpeed: 15
					setLoop: 10
					cel: 0
					setCycle: CycleTo 2 1 self
				)
				(ego cel: 1)
			)
			(16
				(genie setCycle: EndLoop self)
				(ego put: 31 0 cel: 0)
			)
			(17
				(genie cycleSpeed: 3 setLoop: 5 setCycle: EndLoop self)
				(fx number: 943 loop: 1 play:)
			)
			(18
				(genie dispose:)
				(genieSong fade:)
				(ego normal: 1 reset:)
				(= ticks 1)
			)
			(19
				(theConv add: -1 7 67 0 7 init: self)
			)
			(20
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance genieDigging of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(genie setLoop: 0 cycleSpeed: 10 setCycle: Forward)
				(= seconds 4)
			)
			(1 (genie setCycle: EndLoop self))
			(2
				(genie setLoop: 1 setCycle: Forward)
				(= seconds 4)
			)
			(3 (genie setCycle: EndLoop self))
			(4
				(genie setLoop: 2 setCycle: Forward)
				(= seconds 4)
			)
			(5 (genie setCycle: EndLoop self))
			(6 (self init:))
		)
	)
)

(instance genieSummoning of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: genieDigging)
				(theGame handsOff:)
				(curRoom setScript: interruptGenieScript self 6)
			)
			(1 (= seconds 30))
			(2
				(theGame handsOff:)
				(curRoom setScript: interruptGenieScript self 3)
			)
			(3 (= seconds 30))
			(4
				(theGame handsOff:)
				(curRoom setScript: interruptGenieScript self 4)
			)
			(5 (= seconds 30))
			(6
				(if (not (curRoom script?))
					(theGame handsOff:)
					(genie setScript: 0)
					(curRoom setScript: genieMadScript 0 0)
				else
					(= cycles 1)
				)
			)
			(7 (self start: 6 init:))
		)
	)
)

(instance genieOuttaHere of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(genie cycleSpeed: 15 setLoop: 7 setCycle: EndLoop self)
			)
			(1
				(genie setLoop: 8 cel: 0 setCycle: EndLoop self)
			)
			(2 (genie setCycle: BegLoop self))
			(3 (= cycles 2))
			(4 (genie setCycle: EndLoop self))
			(5 (= cycles 2))
			(6 (genie setCycle: BegLoop self))
			(7 (= cycles 2))
			(8 (genie setCycle: EndLoop self))
			(9 (= cycles 2))
			(10
				(fx number: 943 loop: 1 play:)
				(genie cycleSpeed: 3 setLoop: 5 setCycle: EndLoop self)
			)
			(11
				(genieSong fade:)
				(genie dispose:)
				(self dispose:)
			)
		)
	)
)

(instance rosesClose of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 1 0 21 1 self)
			)
			(1
				(fx number: 484 loop: 1 play:)
				(roses setCycle: CycleTo 11 1 self)
			)
			(2
				(roses setLoop: 1)
				((curRoom obstacles?)
					delete: poly1After
					delete: poly2After
					add: (polyHedge type: 2 points: @local88 size: 23 yourself:)
				)
				(messager say: 1 0 21 2 self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance cutEmBaby of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(OneOf state 2 4 5 7 8)
				(!= register (ego cel?))
				(OneOf (ego cel?) 0 2 4 6)
			)
			(= register (ego cel?))
			(fx play:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register -1)
				(fx number: 516 loop: 1)
				(theGame givePoints: 3)
				(ego setMotion: PolyPath 90 88 self)
			)
			(1
				(messager say: 15 16 0 1 self)
			)
			(2
				(roses cycleSpeed: 50 setLoop: 0 cel: 0 setCycle: CycleTo 8 1)
				(ego
					view: 531
					normal: 0
					setLoop: 0
					cel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(3
				(messager say: 15 16 0 2 self)
			)
			(4
				(ego setLoop: 1 setCycle: EndLoop self)
			)
			(5
				(ego setLoop: 0 setCycle: EndLoop self)
			)
			(6
				(messager say: 15 16 0 3 self)
			)
			(7
				(ego setLoop: 1 setCycle: EndLoop self)
			)
			(8
				(ego setLoop: 0 setCycle: EndLoop self)
			)
			(9 (= cycles 7))
			(10
				(messager say: 15 16 0 4 self)
			)
			(11
				(ego reset: 3)
				(Bset 9)
				(= cycles 1)
			)
			(12
				(ego
					ignoreHorizon: 1
					ignoreActors: 1
					setMotion: MoveTo (- (ego x?) 10) (- (ego y?) 10) self
				)
			)
			(13
				(ego setMotion: MoveTo (- (ego x?) 5) (ego y?) self)
			)
			(14 (curRoom newRoom: 540))
		)
	)
)

(instance pickRoseScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 531
					normal: 0
					setLoop: 2
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(ego get: 38 reset: 5)
				(= cycles 1)
			)
			(2
				(if (not (Bset 137)) (theGame givePoints: 1))
				(theGame handsOn:)
				(messager say: 9 5 13)
				(self dispose:)
			)
		)
	)
)

(instance theConv of Conversation
	(properties)
)

(instance egoUseShieldCode of Actions
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(17
				(theGame handsOff:)
				(curRoom setScript: walkPastArcher 0 1)
				(return 1)
			)
		)
		(return 0)
	)
)

(instance poly1Before of Polygon
	(properties)
)

(instance poly2Before of Polygon
	(properties)
)

(instance poly1After of Polygon
	(properties)
)

(instance poly2After of Polygon
	(properties)
)

(instance polyHedge of Polygon
	(properties)
)
