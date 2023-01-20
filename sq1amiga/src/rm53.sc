;;; Sierra Script 1.0 - (do not remove this comment)
(script# 53)
(include sci.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use RandCyc)
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
	[local3 30] = [127 171 196 171 255 135 248 114 241 111 228 124 200 110 207 107 189 100 184 104 164 95 148 102 113 97 71 121 67 145]
	[local33 30] = [127 171 196 171 255 135 248 114 241 111 228 124 200 110 238 82 222 76 184 104 164 95 148 102 113 97 71 121 67 145]
)
(instance rm53 of SQRoom
	(properties
		lookStr {You have fallen into an airlock aboard the Sarien ship Deltaur.}
		picture 53
	)
	
	(method (init)
		(LoadMany 128 34 35 153 456)
		(Load rsPIC 153)
		(LoadMany 130 991 941)
		(LoadMany 132 527 504 520 526 311 525 417)
		(ptsOpenPoly points: @local33 size: 15)
		(ptsClosedPoly points: @local3 size: 15)
		(self addObstacle: ptsClosedPoly)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 142 150 143 142 167 138 175 148 174 154 152 158
					yourself:
				)
		)
		(jetPack init: hide:)
		(machine setCycle: Fwd)
		(self setScript: roomControl)
		(super init:)
	)
	
	(method (doit)
		(cond 
			((ego script?) 0)
			((& (ego onControl: 1) $0200)
				(if (> (droidEnter state?) 4) (ego setScript: walkOut))
				(if (> (droidEnter state?) 11) (Bset 73))
			)
		)
		(super doit: &rest)
	)
)

(instance ptsOpenPoly of Polygon
	(properties
		type $0003
	)
)

(instance ptsClosedPoly of Polygon
	(properties
		type $0003
	)
)

(instance pushEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 6) (HandsOff))
			(1
				(Print 53 0)
				(fireDroid setMotion: 0 ignoreActors: 1)
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
					setCycle: End self
				)
			)
			(2
				(ego setCycle: CT 11 -1 self)
			)
			(3 (ego setCycle: End self))
			(4
				(ego setCycle: CT 11 -1 self)
			)
			(5 (ego setCycle: End self))
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
			((& (fireDroid signal?) $0400) (ego setScript: pushEgo self) (-- state))
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
				(fireDroid init: setCycle: Fwd)
				(= cycles 3)
			)
			(2
				(if (& (ego onControl: 1) $0022) (HandsOff))
				((curRoom obstacles?) delete: ptsClosedPoly)
				((curRoom obstacles?) add: ptsOpenPoly)
				(door setCycle: End self)
			)
			(3
				(fireDroid setMotion: MoveTo 225 90 self)
			)
			(4
				(if (& (ego onControl: 1) $0da2)
					(self setScript: blastEgo self)
				else
					(HandsOn)
					(= cycles 3)
				)
			)
			(5
				(fireDroid setMotion: MoveTo 185 117 self)
			)
			(6
				(fireDroid setMotion: MoveTo 182 138 self)
			)
			(7
				(fireDroid
					setLoop: 1
					cel: 0
					cycleSpeed: 8
					setCycle: CT 2 1 self
				)
			)
			(8
				(= fireDoused 1)
				(soundFx number: 417 loop: 1 play:)
				(fireDroid setLoop: 1 cel: 3 setCycle: End self)
			)
			(9
				(jetPack posn: 148 149 view: 456 setLoop: 2 cel: 0)
				(= cycles 3)
			)
			(10
				(fireDroid setLoop: 4 cel: 0 setCycle: End self)
			)
			(11
				(fireDroid
					setLoop: 3
					cel: 0
					setCycle: Fwd
					cycleSpeed: 5
					setMotion: MoveTo 190 110 self
				)
			)
			(12
				(fireDroid setMotion: MoveTo 230 95 self)
			)
			(13
				(droidSound fade:)
				(if (not (& (ego onControl: 1) $0022))
					(door setCycle: Beg self)
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
				(machine setCycle: Fwd)
				(Face ego fireDroid self)
			)
			(1
				(soundFx number: 312 loop: 1 play:)
				(= saveBits (Graph grSAVE_BOX 30 48 190 265 1))
				(Graph
					grDRAW_LINE
					61
					149
					(- (ego y?) 16)
					(ego x?)
					colLBlue
					1
					0
					0
				)
				(Graph
					grDRAW_LINE
					61
					149
					(- (ego y?) 19)
					(ego x?)
					colLRed
					1
					0
					0
				)
				(Graph
					grDRAW_LINE
					61
					149
					(- (ego y?) 22)
					(ego x?)
					colLGreen
					1
					0
					0
				)
				(Graph grREDRAW_BOX 30 48 190 265)
				(= seconds 1)
			)
			(2
				(Graph grRESTORE_BOX saveBits)
				(Graph grREDRAW_BOX 30 48 190 265)
				(= saveBits 0)
				(= seconds 1)
			)
			(3
				(if (& (ego onControl: 1) $0520) (ego setLoop: 1))
				(if (& (ego onControl: 1) $0880) (ego setLoop: 2))
				(ego view: 71 cel: 0 cycleSpeed: 11 setCycle: End self)
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
				(door init:)
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
				(curRoom overlay: 153 100)
				(theMusic2 number: 504 loop: 1 play: self hold: 1)
				(bayDoor
					init:
					setPri: 0
					cycleSpeed: 25
					setCycle: CT 2 1 self
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
				(HandsOff)
				(fireDroid setMotion: 0 setScript: 0)
				(ego
					ignoreControl: 2
					ignoreActors: 1
					setMotion: MoveTo 230 88 self
				)
			)
			(1 (door setCycle: Beg self))
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
					setCycle: End self
				)
			)
			(3
				(ego cel: 0 setLoop: 1 posn: 89 149 setCycle: CT 3 1 self)
			)
			(4
				(ego setCycle: End)
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
					setCycle: End
					setMotion: PackJump 161 152 self
				)
			)
			(6
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
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
					setCycle: Fwd
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
		onMeCheck $0004
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2 (Print 53 5 #at 40 10))
			(3 (Print 53 6 #at 40 10))
			(12 (Print 53 7 #at 40 10))
			(11 (Print 53 8 #at 40 10))
			(5 (Print 53 9 #at 40 10))
			(4 (Print 53 10 #at 40 10))
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
		signal $0800
		cycleSpeed 5
		moveSpeed 7
	)
	
	(method (init)
		(super init: &rest)
		(self baseSetter: fireDroidBrRect)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2 (Print 53 11 #at 40 10))
			(3 (Print 53 12 #at 40 10))
			(12 (Print 53 13 #at 40 10))
			(11 (Print 53 14 #at 40 10))
			(4
				(switch theItem
					(10 (Print 53 15 #at 40 10))
					(0 (Print 53 16))
					(15 (Print 53 17))
					(2 (Print 53 18))
					(4 (Print 53 19))
					(5 (Print 53 20))
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(5 (Print 53 21 #at 40 10))
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
		priority 5
		signal $4010
		cycleSpeed 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2 (Print 53 22 #at 40 10))
			(3 (Print 53 23 #at 40 10))
			(12 (Print 53 24))
			(11 (Print 53 25))
			(5 (Print 53 26 #at 40 10))
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
			(2 (Print 53 27 #at 40 10))
			(3 (Print 53 28 #at 40 10))
			(12 (Print 53 29 #at 40 10))
			(11 (Print 53 30 #at 40 10))
			(5 (Print 53 31 #at 40 10))
			(4 (Print 53 32 #at 40 10))
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
		signal $4800
		cycleSpeed 4
		moveSpeed 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if (== fireDoused 1)
					(Print 53 33 #at 40 10)
				else
					(Print 53 34 #at 40 10)
				)
			)
			(3
				(if (== fireDoused 1)
					(Print 53 35 #at 40 10)
				else
					(Print 53 36 #at 40 10)
				)
			)
			(12
				(if (== fireDoused 1)
					(Print 53 37 #at 40 10)
				else
					(Print 53 38 #at 40 10)
				)
			)
			(11 (Print 53 39 #at 40 10))
			(5 (Print 53 40 #at 40 10))
			(4
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
		onMeCheck $0004
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2 (Print 53 43 #at 40 10))
			(3 (Print 53 6 #at 40 10))
			(12 (Print 53 44 #at 40 10))
			(11 (Print 53 8 #at 40 10))
			(5 (Print 53 45 #at 40 10))
			(4 (Print 53 10 #at 40 10))
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
