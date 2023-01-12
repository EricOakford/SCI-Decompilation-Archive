;;; Sierra Script 1.0 - (do not remove this comment)
(script# 53)
(include game.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm53 0
)

(local
	fireDoused
	saveBits
	local2
)
(instance rm53 of SQRoom
	(properties
		lookStr {You have fallen into an airlock aboard the Sarien ship Deltaur.}
		picture 53
	)
	
	(method (init)
		(LoadMany VIEW 34 35 153 456)
		(Load PICTURE 153)
		(LoadMany SCRIPT JUMP RANDCYC)
		(LoadMany SOUND 527 504 520 526 311 525 417)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						127 171 196 171 255 135 248 114 240 111 225 120 201 109
						245 81 242 0 319 0 319 189 0 189 0 0 224 0 224 69 204 94
						186 107 164 95 148 102 113 97 71 121 67 145
					yourself:
				)
		)
		(jetPack init: hide:)
		(machine setCycle: Forward)
		(self setScript: roomControl)
		(super init:)
	)
	
	(method (doit)
		(cond 
			((ego script?) 0)
			((& (ego onControl: origin) cLBLUE)
				(if (> (droidEnter state?) 4) (ego setScript: walkOut))
				(if (> (droidEnter state?) 11) (Bset fFlag73))
			)
		)
		(super doit: &rest)
	)
)

(instance pushEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 6) (HandsOff))
			(1
				(Print 53 0)
				(fireDroid setMotion: 0 ignoreActors: TRUE)
				(soundFx number: 527 loop: 1 play:)
				(fireDroid setMotion: 0)
				(ego
					setPri: (ego priority?)
					posn: (ego x?) (- (ego y?) 24)
					view: 70
					setMotion: 0
					cycleSpeed: 8
					setLoop: 0
					cel: 0
					ignoreActors: 1
					setCycle: EndLoop self
				)
			)
			(2
				(ego setCycle: CycleTo 11 -1 self)
			)
			(3 (ego setCycle: EndLoop self))
			(4
				(ego setCycle: CycleTo 11 -1 self)
			)
			(5 (ego setCycle: EndLoop self))
			(6 (= cycles 60))
			(7
				(EgoDead 945 0 0 53 1)
				(self dispose:)
			)
		)
	)
)

(instance droidEnter of Script
	(properties)
	
	(method (doit)
		(cond 
			((ego script?) 0)
			((< state 3) 0)
			((> state 12) 0)
			((& (fireDroid signal?) blocked) (ego setScript: pushEgo self) (-- state))
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(droidSound
					number: 525
					loop: -1
					flags: 1
					play: 30
					fade: 127 25 10 0
				)
				(fireDroid init: setCycle: Forward)
				(= cycles 3)
			)
			(2
				(if (& (ego onControl: origin) (| cBLUE cMAGENTA)) (HandsOff))
				(door setCycle: EndLoop self)
				(door ignoreActors: TRUE)
			)
			(3
				(fireDroid setMotion: PolyPath 225 90 self)
			)
			(4
				(if (& (ego onControl: origin) $0da2)
					(self setScript: blastEgo self)
				else
					(HandsOn)
					(= cycles 3)
				)
			)
			(5
				(fireDroid setMotion: PolyPath 185 117 self)
			)
			(6
				(fireDroid setMotion: PolyPath 182 138 self)
			)
			(7
				(fireDroid
					setLoop: 1
					cel: 0
					cycleSpeed: 8
					setCycle: CycleTo 2 1 self
				)
			)
			(8
				(= fireDoused TRUE)
				(soundFx number: 417 loop: 1 play:)
				(fireDroid setLoop: 1 cel: 3 setCycle: EndLoop self)
			)
			(9
				(jetPack posn: 148 149 view: 456 setLoop: 2 cel: 0)
				(= cycles 3)
			)
			(10
				(fireDroid setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(11
				(fireDroid
					setLoop: 3
					cel: 0
					setCycle: Forward
					cycleSpeed: 5
					setMotion: PolyPath 190 110 self
				)
			)
			(12
				(fireDroid setMotion: PolyPath 230 95 self)
			)
			(13
				(droidSound fade:)
				(if (not (& (ego onControl: origin) (| cBLUE cMAGENTA)))
					(door ignoreActors: 0 setCycle: BegLoop self)
				)
			)
			(14
				(self dispose:)
				(EgoDead 945 0 0 53 2)
			)
		)
	)
)

(instance fireDroidBrRect of Code
	(properties)
	
	(method (doit param1)
		(if (<= (param1 loop?) 2)
			(param1
				brLeft: (- (param1 x?) 22)
				brTop: (- (param1 y?) 12)
				brRight: (+ (param1 x?) 17)
				brBottom: (+ (param1 y?) 7)
			)
		else
			(param1
				brLeft: (- (param1 x?) 17)
				brTop: (- (param1 y?) 12)
				brRight: (+ (param1 x?) 22)
				brBottom: (+ (param1 y?) 7)
			)
		)
	)
)

(instance blastEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 53 3 #at 40 10)
				(ego setMotion: 0)
				(machine setCycle: Forward)
				(Face ego fireDroid self)
			)
			(1
				(soundFx number: 312 loop: 1 play:)
				(= saveBits (Graph GSaveBits 30 48 190 265 1))
				(Graph GDrawLine
					61
					149
					(- (ego y?) 16)
					(ego x?)
					global152
					1
					0
					0
				)
				(Graph
					GDrawLine
					61
					149
					(- (ego y?) 19)
					(ego x?)
					colDRed
					1
					0
					0
				)
				(Graph
					GDrawLine
					61
					149
					(- (ego y?) 22)
					(ego x?)
					myTextColor7
					1
					0
					0
				)
				(Graph GReAnimate 30 48 190 265)
				(= seconds 2)
			)
			(2
				(Graph GRestoreBits saveBits)
				(Graph GReAnimate 30 48 190 265)
				(= saveBits 0)
				(= seconds 1)
			)
			(3
				(if (& (ego onControl: origin) $0520) (ego setLoop: 1))
				(if (& (ego onControl: origin) $0880) (ego setLoop: 2))
				(ego view: 71 cel: 0 cycleSpeed: 11 setCycle: EndLoop self)
			)
			(4 (= seconds 3))
			(5
				(EgoDead 940 1 0 53 4)
				(self dispose:)
			)
		)
	)
)

(instance roomControl of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					init:
					hide:
					cel: 0
					view: 34
					posn: 77 33
					setLoop: 12
					setStep: 6 6
					normal: 0
				)
				(HandsOff)
				(door init: ignoreActors: FALSE)
				(doorPanel_a init:)
				(machine init:)
				(ego setScript: zoomIn self)
			)
			(1 (= seconds 3))
			(2
				(self setScript: CloseDoors self)
			)
			(3 (= seconds 10))
			(4
				(self setScript: droidEnter self)
			)
			(5 (self dispose:))
		)
	)
)

(instance CloseDoors of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom overlay: 153 PLAIN)
				(theMusic2 number: 504 loop: 1 play: self hold: 1)
				(bayDoor
					init:
					setPri: 0
					cycleSpeed: 25
					setCycle: CycleTo 2 1 self
				)
			)
			(1
				(theMusic2 hold: 0)
				(bayDoor dispose:)
			)
			(2
				(theMusic number: 520 loop: 1 play: self)
			)
			(3
				(theMusic number: 538 loop: -1 play:)
				(self dispose:)
			)
		)
	)
)

(instance walkOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fireDroid setMotion: 0 setScript: 0)
				(ego
					ignoreControl: 2
					ignoreActors: 1
					setMotion: PolyPath 208 77 self
				)
				(HandsOff)
			)
			(1
				(door ignoreActors: 0 setCycle: BegLoop self)
				(ego setMotion: PolyPath 220 77)
			)
			(2 (curRoom newRoom: 54))
		)
	)
)

(instance zoomIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 6))
			(1
				(soundFx number: 505 loop: 1 play:)
				(ego
					show:
					setCycle: RandCycle
					setMotion: MoveTo 102 96 self
				)
			)
			(2
				(ego
					cel: 0
					view: 35
					setLoop: 0
					cycleSpeed: 5
					setCycle: EndLoop self
				)
			)
			(3
				(ego cel: 0 setLoop: 1 posn: 89 149 setCycle: CycleTo 3 1 self)
			)
			(4
				(ego setCycle: EndLoop)
				(jetPack
					show:
					moveSpeed: 4
					setCycle: RandCycle
					setMotion: PackJump 185 130 self
				)
			)
			(5
				(soundFx number: 526 loop: 1 play:)
				(jetPack
					cel: 0
					setCycle: EndLoop
					setMotion: PackJump 161 152 self
				)
			)
			(6
				(curRoom
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init: 142 150 143 142 167 138 175 148 174 154 152 158
							yourself:
						)
				)
				(soundFx number: 526 loop: 1 play:)
				(jetPack
					posn: 138 149
					view: 153
					setLoop: 1
					cel: 0
					setCycle: Forward
				)
				(= ticks 18)
			)
			(7
				(HandsOn)
				(ego put: 11 53)
				(NormalEgo 0 1 61)
				(self dispose:)
			)
		)
	)
)

(instance PackJump of JumpTo
	(properties)
	
	(method (doit)
		(if (>= (++ b-moveCnt) (client moveSpeed?))
			(= b-moveCnt 0)
			(super doit:)
		)
	)
)

(instance doorPanel_a of Feature
	(properties
		description {Keycard slot}
		onMeCheck FARCHECK
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 53 5
					#at 40 10
				)
			)
			(verbDo
				(Print 53 6
					#at 40 10
				)
			)
			(verbSmell
				(Print 53 7
					#at 40 10
				)
			)
			(verbTaste
				(Print 53 8
					#at 40 10
				)
			)
			(verbTalk
				(Print 53 9
					#at 40 10
				)
			)
			(verbUse
				(Print 53 10
					#at 40 10
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fireDroid of Actor
	(properties
		x 238
		y 81
		view 456
		signal fixedLoop
		cycleSpeed 5
		moveSpeed 7
	)
	
	(method (init)
		(super init: &rest)
		(self baseSetter: fireDroidBrRect)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 53 11
					#at 40 10
				)
			)
			(verbDo
				(Print 53 12
					#at 40 10
				)
			)
			(verbTaste
				(Print 53 13
					#at 40 10
				)
			)
			(verbSmell
				(Print 53 14
					#at 40 10
				)
			)
			(verbUse
				(switch theItem
					(iBuckazoid
						(Print 53 15
							#at 40 10
						)
					)
					(iCartridge
						(Print 53 16)
					)
					(iWidget
						(Print 53 17)
					)
					(iGadget
						(Print 53 18)
					)
					(iKnife
						(Print 53 19)
					)
					(iDehydratedWater
						(Print 53 20)
					)
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(verbTalk
				(Print 53 21
					#at 40 10
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance door of Prop
	(properties
		x 204
		y 103
		view 153
		cycleSpeed 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 53 22 #at 40 10)
			)
			(verbDo
				(Print 53 23 #at 40 10)
			)
			(verbSmell
				(Print 53 24)
			)
			(verbTaste
				(Print 53 25)
			)
			(verbTalk
				(Print 53 26
					#at 40 10
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (setCycle)
		(super setCycle: &rest)
		(doorSound number: 311 loop: 1 play:)
	)
)

(instance machine of Prop
	(properties
		x 146
		y 68
		onMeCheck $4000
		view 153
		loop 2
		cycleSpeed 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 53 27
					#at 40 10
				)
			)
			(verbDo
				(Print 53 28
					#at 40 10
				)
			)
			(verbSmell
				(Print 53 29
					#at 40 10
				)
			)
			(verbTaste
				(Print 53 30
					#at 40 10
				)
			)
			(verbTalk
				(Print 53 31
					#at 40 10
				)
			)
			(verbUse
				(Print 53 32
					#at 40 10
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance jetPack of Actor
	(properties
		x 116
		y 134
		view 35
		loop 2
		signal (| ignrAct fixedLoop)
		cycleSpeed 4
		moveSpeed 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (== fireDoused 1)
					(Print 53 33 #at 40 10)
				else
					(Print 53 34 #at 40 10)
				)
			)
			(verbDo
				(if (== fireDoused 1)
					(Print 53 35 #at 40 10)
				else
					(Print 53 36 #at 40 10)
				)
			)
			(verbSmell
				(if (== fireDoused 1)
					(Print 53 37 #at 40 10)
				else
					(Print 53 38 #at 40 10)
				)
			)
			(verbTaste
				(Print 53 39 #at 40 10)
			)
			(verbTalk
				(Print 53 40 #at 40 10)
			)
			(verbUse
				(if (== fireDoused 1)
					(Print 53 41 #at 40 10)
				else
					(Print 53 42 #at 40 10)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance doorPanel_b of Feature
	(properties
		description {Keycard slot}
		onMeCheck FARCHECK
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 53 43 #at 40 10)
			)
			(verbDo
				(Print 53 6 #at 40 10)
			)
			(verbSmell
				(Print 53 44 #at 40 10)
			)
			(verbTaste
				(Print 53 8 #at 40 10)
			)
			(verbTalk
				(Print 53 45 #at 40 10)
			)
			(verbUse
				(Print 53 10 #at 40 10)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance doorSound of Sound
	(properties
		number 311
	)
)

(instance bayDoor of Prop
	(properties
		x 141
		y 114
		view 153
		loop 3
	)
)

(instance droidSound of Sound
	(properties
		number 525
	)
)
