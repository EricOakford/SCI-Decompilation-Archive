;;; Sierra Script 1.0 - (do not remove this comment)
(script# 704)
(include sci.sh)
(use Main)
(use Intrface)
(use RegionPath)
(use PChase)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Reverse)
(use Grooper)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	keronaRegion 0
	plant 1
	plant2 2
	spider 3
	drinkScript 4
)

(local
	landX
	landY
	landPri =  7
	roomSound
	fellDown
	gEgoCel
	[spiderPathID 141] = [32767 26 350 175 162 155 82 -10 32767 23 82 220 95 169 157 132 248 167 34 95 108 -7 95 183 18 -10 32767 20 18 220 62 167 32767 19 350 167 278 220 32767 22 310 48 350 118 32767 23 -10 118 94 183 97 183 55 182 43 220 32767 26 43 -10 74 90 -10 127 32767 25 350 127 113 160 35 105 23 43 101 -10 32767 22 101 200 2 114 22 -10 32767 19 22 220 32 141 60 80 152 49 350 32 32767 20 -10 32 168 41 135 59 197 75 350 54 32767 21 -10 54 241 75 297 111 280 220 32767 24 280 -10 95 76 141 146 181 220 32767 27 167 -10 161 49 227 141 186 170 -10 175 -32768]
	[pts1 24] = [-50 300 48 189 102 115 213 65 319 57 700 20 700 180 319 163 298 173 258 178 257 189 350 300]
	[pts2 22] = [-450 3 0 53 163 88 800 40 800 140 319 99 319 181 208 175 149 153 0 158 -450 180]
	[pts3 24] = [-250 50 0 103 127 96 217 118 295 140 287 172 169 175 134 189 62 189 41 176 0 181 -250 200]
	[pts4 8] = [319 189 297 189 277 165 319 146]
	[pts5 28] = [120 189 84 170 64 130 21 127 0 103 14 72 62 60 62 26 47 0 167 0 312 108 318 140 270 162 285 189]
	[pts6 8] = [0 189 0 159 34 167 16 189]
	[pts7 18] = [60 300 85 189 106 166 269 165 216 147 203 120 319 64 500 40 500 300]
	[pts8 18] = [319 129 144 138 124 109 209 109 190 99 120 102 104 81 199 61 319 54]
	[pts9 12] = [-250 300 -250 10 0 38 64 73 155 189 250 300]
	[pts10 12] = [268 0 279 11 258 32 165 32 129 8 129]
	[pts11 18] = [262 144 143 144 54 99 18 63 18 36 278 36 317 75 317 113 262 113]
	[pts12 36] = [60 -80 400 -80 400 85 319 61 281 65 267 86 319 126 319 157 294 173 259 164 238 146 241 131 170 99 170 69 202 55 223 23 127 20 82]
	[pts13 16] = [100 -80 123 0 219 146 115 165 34 135 0 53 -100 90 -100 -80]
	[pts14 14] = [263 35 316 88 285 119 224 119 177 72 177 69 177 35]
)
(procedure (localproc_005e param1)
	(= roomSound
		(cond 
			((OneOf param1 18 37 38 138 238 338) 401)
			((OneOf param1 19 20 21 22 23 24 25 26 27) 448)
			((OneOf param1 28) (if (Btst 59) 420 else 415))
		)
	)
)

(procedure (localproc_00c8)
	(spiderPoly release:)
	(switch curRoomNum
		(19
			(spiderPolyA points: @pts1 size: 12)
			(spiderPoly add: spiderPolyA)
		)
		(20
			(spiderPolyA points: @pts2 size: 11)
			(spiderPoly add: spiderPolyA)
		)
		(21
			(spiderPolyA points: @pts3 size: 12)
			(spiderPoly add: spiderPolyA)
		)
		(22
			(spiderPolyA points: @pts4 size: 4)
			(spiderPolyB points: @pts5 size: 14)
			(spiderPoly add: spiderPolyA spiderPolyB)
		)
		(23
			(spiderPolyA points: @pts6 size: 4)
			(spiderPolyB points: @pts7 size: 9)
			(spiderPoly add: spiderPolyA spiderPolyB)
		)
		(24
			(spiderPolyA points: @pts8 size: 9)
			(spiderPolyB points: @pts9 size: 6)
			(spiderPoly add: spiderPolyA spiderPolyB)
		)
		(25
			(spiderPolyA points: @pts10 size: 6)
			(spiderPolyB points: @pts11 size: 9)
			(spiderPoly add: spiderPolyA spiderPolyB)
		)
		(26
			(spiderPolyA points: @pts12 size: 18)
			(spiderPoly add: spiderPolyA)
		)
		(27
			(spiderPolyA points: @pts13 size: 8)
			(spiderPolyB points: @pts14 size: 7)
			(spiderPoly add: spiderPolyA spiderPolyB)
		)
	)
)

(instance spiderPolyA of Polygon
	(properties
		type $0002
	)
)

(instance spiderPolyB of Polygon
	(properties
		type $0002
	)
)

(instance keronaRegion of Rgn
	(properties)
	
	(method (init &tmp [temp0 3])
		(if
			(or
				(not (Btst 55))
				(== currentFloor 1)
				(!= prevRoomNum spiderRoom)
			)
			(= spiderRoom 0)
		else
			(LoadMany 132 411 361)
			(= spiderRoom curRoomNum)
		)
		(cond 
			(
			(not (if (<= 19 curRoomNum) (<= curRoomNum 27))) 0)
			(spiderRoom
				(localproc_00c8)
				(spider
					posn:
						(switch (ego edgeHit?)
							(4 (+ spiderX 319))
							(2 (- spiderX 319))
							(else  droidX)
						)
						(switch (ego edgeHit?)
							(3 (- spiderY 190))
							(1 (+ spiderY 190))
							(else  spiderY)
						)
					init:
					show:
					moveSpeed: (+ howFast 2)
					cycleSpeed: (+ howFast 2)
					z: 0
					view: 422
					setCycle: Walk
					setLoop: -1
					setLoop: spiderGroop
					setMotion: PChase ego 24 spider spiderPoly
				)
			)
			((Btst 55)
				(spider
					view: 422
					setLoop: -1
					moveSpeed: (+ howFast 2)
					cycleSpeed: (+ howFast 2)
					setLoop: spiderGroop
					setCycle: Walk
					regionPathID: spiderRegionPather
					setMotion: spiderRegionPather
					init:
				)
			)
		)
		(if (not (ego has: 16)) (Load rsVIEW 13))
		(LoadMany 128 15 16)
		(if (or spiderRoom spiderTimer (Btst 55))
			(LoadMany 128 422 328 56)
			(LoadMany 132 403 404)
			(Load rsSCRIPT 930)
		)
		(super init: &rest)
		(mountains init:)
		(keronaSky init:)
		(moon init:)
		(farSand init:)
		(nearSand init:)
		(dune init:)
		(bones init:)
		(base init:)
		(plant init:)
		(if (== curRoomNum 22) (plant2 init:))
		(if
			(and
				(!= (theMusic number?) (localproc_005e curRoomNum))
				(not spiderRoom)
			)
			(theMusic
				number: (if (spider inThisRoom:) 404 else roomSound)
				loop: -1
				play:
			)
		)
	)
	
	(method (doit)
		(if
			(and
				(== (theMusic number?) roomSound)
				(or spiderRoom (and (Btst 55) (spider inThisRoom:)))
			)
			(theMusic number: 404 loop: -1 play:)
		)
		(if
			(and
				(== (theMusic prevSignal?) -1)
				(== (theMusic number?) 404)
			)
			(theMusic number: roomSound loop: -1 play:)
		)
		(cond 
			((curRoom script?) 0)
			(
				(and
					spiderRoom
					(not fellDown)
					(!= curRoomNum 27)
					(OneOf (ego loop?) 5 4)
					(& (ego onControl: 1) $0002)
				)
				(= fellDown 1)
				(curRoom setScript: egoFallDown 0 (== (ego loop?) 5))
			)
			(spiderTimer
				(if
					(and
						(<= 19 curRoomNum)
						(<= curRoomNum 27)
						(not (-- spiderTimer))
					)
					(if
						(or
							(== currentFloor 1)
							(< (ego y?) 90)
							(and (OneOf 21 32) (< (ego x?) 160))
							(> (ego x?) 180)
						)
						(curRoom setScript: spiderFalls)
					else
						(= spiderTimer 40)
					)
				)
			)
			(thirstTimer
				(if (not (-- thirstTimer))
					(if
						(and
							(ego inRect: 40 30 180 190)
							(not (curRoom script?))
						)
						(curRoom setScript: egoDiesOfThirst)
					else
						(= thirstTimer 50)
					)
				else
					(switch thirstTimer
						(1200 (Print 704 0))
						(500 (Print 704 1))
					)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(spiderPoly dispose:)
		(super dispose: &rest)
	)
	
	(method (newRoom n)
		(spiderPoly release:)
		(= keep (OneOf n 37 18 19 20 21 22 23 24 25 26 27))
		(= initialized 0)
		(if
			(and
				(not spiderRoom)
				(IsObject (spider regionPathID?))
				(!= ((spider regionPathID?) currentRoom?) n)
				(== (theMusic number?) 404)
			)
			(theMusic fade:)
		)
		(= fellDown 0)
		(super newRoom: n &rest)
	)
)

(instance mountains of Feature
	(properties
		description {mountains}
		onMeCheck $0080
		lookStr {Mountains generously dappled with spires of unknown formational origin loom at your visual terminus.}
	)
	
	(method (doVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(super doVerb: &rest)
	)
)

(instance keronaSky of Feature
	(properties
		description {sky}
		onMeCheck $0020
		lookStr {The lovely crisp green of the Keronian atmosphere reminds you that you'll need to do some laundry when (or if) you get back home.}
	)
	
	(method (doVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(super doVerb: &rest)
	)
)

(instance moon of Feature
	(properties
		description {big moon}
		onMeCheck $0040
		lookStr {Loitering about the horizon is the second and closest moon of Kerona. It is much less hospitable than the sphere you presently roam.}
	)
	
	(method (doVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(super doVerb: &rest)
	)
)

(instance farSand of Feature
	(properties
		description {distant desert}
		onMeCheck $0100
		lookStr {Spread before you is a seemingly endless expanse of sand.}
	)
	
	(method (doVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(super doVerb: &rest)
	)
)

(instance nearSand of Feature
	(properties
		description {sand}
		onMeCheck $0200
		lookStr {The sand of Kerona has a reddish-orange color. Were you a geologist instead of a janitor, you might find this fact interesting.}
	)
	
	(method (doVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(super doVerb: &rest)
	)
)

(instance dune of Feature
	(properties
		description {small dune}
		onMeCheck $0002
		lookStr {Nomads of the desert, dunes are spread about in nearly every direction.}
	)
	
	(method (doVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(super doVerb: &rest)
	)
)

(instance bones of Feature
	(properties
		description {bones}
		onMeCheck $0010
		lookStr {A wide selection of skeletal accessories awaits your perusal. Vertebrae, ribs, and who knows what else rest upon the Keronian soil. The average height of this structure looks to be at least 7 meters. Good thing you didn't meet this thing in its heyday.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3 (Print 704 2))
			(11 (Print 704 3))
			(5 (Print 704 4))
			(12 (Print 704 5))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance base of Feature
	(properties
		description {sandy base}
		onMeCheck $0008
		lookStr {Upon a very narrow width of Keronian soil, able to resist erosion through the eons, rests the skeletal remains of this mammoth has-been.}
	)
	
	(method (doVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(super doVerb: &rest)
	)
)

(instance plant of Feature
	(properties
		description {plant}
		onMeCheck $0004
		lookStr {Oddly enough, a plant grows in isolated spots in this inhospitable environment.}
	)
	
	(method (init)
		(if (== currentFloor 2) (self approachVerbs: 3))
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3
				(cond 
					((ego has: 16) (Print 704 6))
					(
					(or (== (curRoom script?) bigBang) (== currentFloor 1)) (super doVerb: theVerb &rest))
					(else (curRoom setScript: pickPlant))
				)
			)
			(12 (Print 704 7))
			(11 (Print 704 8))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance plant2 of Feature
	(properties
		description {plant}
		onMeCheck $4000
		lookStr {Oddly enough, a rather bright plant grows in this otherwise flora-free area.}
	)
	
	(method (init)
		(if (== currentFloor 2) (self approachVerbs: 3))
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3
				(cond 
					((ego has: 16) (Print 704 6))
					(
					(or (== (curRoom script?) bigBang) (== currentFloor 1)) (super doVerb: theVerb &rest))
					(else (curRoom setScript: pickPlant))
				)
			)
			(12 (Print 704 9))
			(11 (Print 704 8))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pickPlant of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== state 1)
				(OneOf (ego cel?) 4 10)
				(!= gEgoCel (ego cel?))
			)
			(if (== (soundFx number?) 456)
				(soundFx number: 457 loop: 1 play:)
			else
				(soundFx number: 456 loop: 1 play:)
			)
		)
		(= gEgoCel (ego cel?))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 13
					cel: 0
					loop: 0
					cycleSpeed: 15
					setCycle: End self
				)
			)
			(1
				(Print 704 10)
				(ego loop: 1 cel: 0 cycleSpeed: 25 setCycle: End self)
			)
			(2
				(ego loop: 3 cel: 0 cycleSpeed: 15 setCycle: End self)
			)
			(3
				(if (== (curRoom script?) self)
					(NormalEgo 0 1 61)
					(ego loop: 0)
					(SolvePuzzle 2 142)
					(ego get: 16)
					(Print 704 11)
					(HandsOn)
				)
				(self dispose:)
			)
		)
	)
)

(instance egoFallDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 56
					setLoop: register
					cel: 0
					cycleSpeed: 12
					setCycle: CT 6 1 self
				)
			)
			(1
				(soundFx number: 403 loop: 1 play:)
				(ego setCycle: End self)
			)
			(2 (= cycles 14))
			(3
				(ego
					x: (if register (- (ego x?) 33) else (+ (ego x?) 33))
					y: (+ (ego y?) 11)
					setLoop: (+ register 2)
					cel: 0
					setCycle: End self
				)
			)
			(4
				(if (== (curRoom script?) self)
					(NormalEgo 0 1 61)
					(ego loop: (if register 5 else 4))
					(HandsOn)
				)
				(self dispose:)
			)
		)
	)
)

(instance egoDiesOfThirst of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setMotion: 0
					setHeading:
						(if
						(and (< 0 (ego heading?)) (< (ego heading?) 181))
							90
						else
							270
						)
						self
				)
			)
			(1
				(Print 704 12)
				(Print 704 13)
				(Print 704 14)
				(= cycles 12)
			)
			(2
				(ego view: 15 cel: 0 cycleSpeed: 15 setCycle: End self)
			)
			(3 (= cycles 12))
			(4 (EgoDead 948 0 0 704 15))
		)
	)
)

(instance drinkScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= thirstTimer 8000)
				(ego
					setMotion: 0
					setHeading:
						(if
						(and (< 0 (ego heading?)) (< (ego heading?) 181))
							90
						else
							270
						)
						self
				)
			)
			(1
				(ego view: 16 cel: 0 cycleSpeed: 15 setCycle: End self)
			)
			(2
				(ego
					setLoop: (+ (ego loop?) 2)
					cel: 0
					setCycle: Osc 1 self
				)
			)
			(3
				(Print 704 16)
				(ego
					setLoop: (- (ego loop?) 2)
					cel: 9
					setCycle: Beg self
				)
			)
			(4
				(if (== (curRoom script?) self)
					(= register (ego loop?))
					(NormalEgo 0 1 61)
					(ego loop: register)
					(if (== currentFloor 1) (ego setPri: 14))
					(HandsOn)
				)
				(self dispose:)
			)
		)
	)
)

(instance spiderFalls of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(Print 704 17)
				(spiderSound number: 470 loop: 1 play:)
				(switch curRoomNum
					(19 (= landX -20))
					(20
						(= landY 40)
						(= landPri 13)
					)
					(21
						(= landX 200)
						(= landY -40)
						(= landPri 5)
					)
					(22
						(= landY 20)
						(= landPri 11)
					)
					(24
						(= landX 200)
						(= landY 30)
						(= landPri 12)
					)
					(27 (= landX -20))
				)
				(= seconds 2)
			)
			(1
				(spider init: hide:)
				(shadow
					posn: (+ landX 2) (+ landY 63)
					init:
					setPri: landPri
					setMotion: MoveTo (+ 32 landX) (+ landY 80) self
				)
				(ego setMotion: 0)
				(Face ego shadow)
			)
			(2
				(spider
					setPri: landPri
					posn: (+ landX 41) (+ landY 62)
					show:
				)
				(shadow posn: (+ landX 41) (+ landY 118))
				(= cycles (+ 3 howFast))
			)
			(3
				(spider posn: (+ landX 66) (+ landY 114))
				(shadow cel: 1 posn: (+ landX 66) (+ landY 131))
				(= cycles (+ 3 howFast))
			)
			(4
				(spider hide:)
				(spiderSound number: 471 loop: 1 play:)
				(ego setMotion: 0)
				(Face ego shadow)
				(shadow cel: 2 cycleSpeed: 26 setCycle: End self)
			)
			(5
				(shadow dispose:)
				(spider
					posn: (+ landX 66) (+ landY 131)
					show:
					setLoop: 1
					cel: 0
					cycleSpeed: 26
					setCycle: End self
				)
				(Bset 55)
			)
			(6
				(Print 704 18)
				(Print 704 19)
				(spider
					view: 422
					setLoop: -1
					setLoop: spiderGroop
					loop: 2
					heading: 180
					cycleSpeed: (+ howFast 2)
					moveSpeed: (+ howFast 2)
					setPri: -1
					setCycle: Walk
				)
				(localproc_00c8)
				(if (== currentFloor 2)
					(spider z: 0 setMotion: PChase ego 24 spider spiderPoly)
					(= spiderRoom curRoomNum)
					(++ state)
					(self cue:)
				else
					(spider
						setMotion: PolyPath (spider x?) 240 self 1 spiderPoly
					)
					(= spiderRoom 0)
				)
			)
			(7
				(spider
					regionPathID: spiderRegionPather
					setMotion: spiderRegionPather
				)
				(= cycles 1)
			)
			(8 (HandsOn) (self dispose:))
		)
	)
)

(instance bigBang of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theMusic fade:)
				(soundFx number: 411 loop: 1 play:)
				(spider
					view: 328
					setLoop: 0
					cel: 0
					setMotion: 0
					cycleSpeed: 16
					setCycle: CT 2 1 self
				)
				(if (== curRoomNum 27) (spider setPri: 14))
			)
			(1
				(ego dispose:)
				(spider
					posn: (= droidX (ego x?)) (= droidY (+ (ego y?) 1))
					setCycle: End self
				)
			)
			(2 (= seconds 4))
			(3
				(spider
					setPri: (spider priority?)
					view: 423
					posn: droidX -6
					setLoop: 6
					setStep: 1 10
					cycleSpeed: 7
					moveSpeed: 3
					setCycle: Fwd
					setMotion: MoveTo droidX (- droidY 7) self
				)
			)
			(4
				(soundFx number: 361 loop: 1 play:)
				(spider
					cycleSpeed: 5
					setCycle: Rev
					setStep: 1 3
					setMotion: MoveTo droidX (- droidY 20) self
				)
			)
			(5
				(spider setMotion: MoveTo droidX (- droidY 7) self)
			)
			(6
				(soundFx number: 361 loop: 1 play:)
				(spider
					cycleSpeed: 14
					setCycle: Fwd
					setStep: 1 2
					setMotion: MoveTo droidX (- droidY 15) self
				)
			)
			(7
				(spider setMotion: MoveTo droidX (- droidY 7) self)
			)
			(8
				(soundFx number: 361 loop: 1 play:)
				(spider cycleSpeed: 20 setCycle: CT 3 -1 self)
			)
			(9 (= ticks 30))
			(10 (EgoDead 948 0 0 704 20))
		)
	)
)

(class spiderRP of Actor
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		description 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 26505
		lookStr 0
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
		cycleSpeed 3
		script 0
		cycler 0
		timer 0
		detailLevel 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		moveSpeed 3
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
		regionPathID 0
	)
	
	(method (pushToNextRoom)
		(if regionPathID
			(while
				(and
					(!=
						(regionPathID at: (+ (regionPathID value?) 1))
						32767
					)
					(not (regionPathID atEnd:))
				)
				(regionPathID next:)
			)
			(regionPathID moveDone:)
		)
	)
	
	(method (inThisRoom)
		(return
			(if (IsObject regionPathID)
				(return (== curRoomNum (regionPathID currentRoom?)))
			else
				(return (cast contains: self))
			)
		)
	)
)

(instance spider of spiderRP
	(properties
		description {spider droid}
		lookStr {The spider is quite relentless in its pursuit of organic beings. Your quandry is that, to the best of your knowledge, you are the only one in the area fitting that description.}
		view 423
		cel 5
		signal $7800
		illegalBits $0000
	)
	
	(method (doit)
		(cond 
			((curRoom script?) 0)
			((and (== currentFloor 1) spiderRoom) (self setScript: moveToPath))
			(
				(and
					(not spiderRoom)
					(self inThisRoom:)
					(== currentFloor 2)
				)
				(= spiderRoom curRoomNum)
				(localproc_00c8)
				(self z: 0 setMotion: PChase ego 24 spider spiderPoly)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(= spiderX x)
		(= spiderY y)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (Print 704 21))
			(5
				(Print 704 22)
				(Print 704 23)
			)
			(11 (Print 704 24))
			(12 (Print 704 25))
			(4
				(switch theItem
					(10 (Print 704 26))
					(0 (Print 704 27))
					(15 (Print 704 28))
					(2 (Print 704 29))
					(3 (Print 704 30))
					(5 (Print 704 31))
					(4 (Print 704 32))
					(16 (Print 704 33))
					(8 (Print 704 34))
					(else  (Print 704 35))
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (cue)
		(if
		(and spiderRoom (<= (self distanceTo: ego) 25))
			(curRoom setScript: bigBang)
		else
			(super cue: &rest)
		)
	)
)

(instance spiderGroop of Grooper
	(properties)
)

(instance spiderRegionPather of RegionPath
	(properties
		theRegion 704
	)
	
	(method (nextRoom)
		(super nextRoom: &rest)
		(cond 
			((== currentRoom curRoomNum) (theMusic number: 404 loop: -1 play:))
			((== (theMusic number?) 404) (theMusic fade:))
		)
	)
	
	(method (at param1)
		(return [spiderPathID param1])
	)
)

(instance moveToPath of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(= spiderRoom 0)
				(spider setMotion: PolyPath -10 180 self 1 spiderPoly)
			)
			(1
				(spider
					regionPathID: spiderPathID
					setMotion: spiderRegionPather
				)
				(self dispose:)
			)
		)
	)
)

(instance shadow of Actor
	(properties
		yStep 6
		view 423
		signal $7800
		cycleSpeed 7
		xStep 6
		moveSpeed 7
	)
)

(instance spiderSound of Sound
	(properties)
)
