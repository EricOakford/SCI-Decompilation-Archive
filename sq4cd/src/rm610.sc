;;; Sierra Script 1.0 - (do not remove this comment)
(script# 610)
(include game.sh)
(use Main)
(use ulence)
(use SQRoom)
(use Sq4Feature)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Sound)
(use Motion)
(use System)

(public
	rm610 0
)

(instance rm610 of SQRoom
	(properties
		picture 610
		horizon 110
		north 613
		east 611
		west 609
	)
	
	(method (init)
		(switch prevRoomNum
			(615
				(ego posn: 206 135)
				(self setScript: exitBar)
			)
			(620
				(ego
					posn: 192 113
					z: 1000
					view: 615
					setLoop: 0
					cel: 0
					setPri: 9
					looper: 0
					normal: 0
					setStep: 14 14
					cycleSpeed: 6
				)
				(self setScript: flyOut)
			)
			(613 (ego x: 87 y: 124))
			(else  (ego x: 24 y: 165))
		)
		(if (not (OneOf prevRoomNum 615 620))
			(globalSound vol: 70 number: 804 loop: -1 playBed:)
		)
		(if (not (Btst fKickBikes))
			(bike1 init:)
			(bike2 init:)
			(bike3 init:)
			((ScriptID ULENCE 1) init:)
			(biker2 init:)
			(biker3 init:)
		)
		(theSign init: setCycle: Forward)
		(ego init: ignoreActors: 1 illegalBits: 0)
		(super init:)
		(self
			setRegions: ULENCE
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0 319 0 319 158 295 158 295 162 283 162 253 162 253 158
						225 158 225 148 207 148 207 142 176 142 176 135 214 135 214 132
						149 132 131 126 123 121 94 103 68 103 73 123 63 131 44 136 0 136
					yourself:
				)
		)
		(ship1 init:)
		(ship3 init:)
		(building init:)
		(door init:)
		(if (not (Btst fKickBikes))
			(self
				addObstacle:
					((Polygon new:)
						type: PTotalAccess
						init:
							129 153 177 151 210 165 249 185 191 187
							170 180 153 170 136 171 124 160
						yourself:
					)
			)
		)
		(Load SOUND 804)
		(Load VIEW 630)
		(Load VIEW 631)
		(Load VIEW 615)
		(Load VIEW 610)
		(Load SOUND 147)
		(Load SOUND 836)
		(if (== (ulence status?) 1)
			(ulence status: 2 bikerComing: TRUE)
			(Load VIEW 632)
			(Load VIEW 634)
			(Load VIEW 633)
			(Load VIEW 635)
			(Load SOUND 50)
			(Load SOUND 837)
			(Load SOUND 841)
			(ulence status: 2)
		else
			(ulence bikerComing: FALSE)
		)
		((ScriptID ULENCE 8)
			init:
			nsLeft: 0
			nsTop: -1
			nsBottom: 86
			nsRight: 319
		)
		((ScriptID ULENCE 7)
			init:
			nsLeft: 0
			nsTop: 88
			nsBottom: 189
			nsRight: 319
		)
	)
	
	(method (doit)
		(if
			(and
				(== (ulence status?) 4)
				(< ((ScriptID ULENCE 1) distanceTo: ego) 20)
			)
			(HandsOff)
			(ulence status: 6)
			(ego setScript: 0)
			(ulence deathLoop: 1)
			(self setScript: (ScriptID ULENCE 3))
		)
		(cond 
			(script 0)
			((== (ego edgeHit?) NORTH) (globalSound fade: 0 30 5 0))
			((== (ego edgeHit?) EAST) (globalSound fade: 0 30 5 0))
			((== (ego edgeHit?) WEST) (globalSound fade: 0 30 5 0))
			((ego inRect: 192 122 221 137)
				(if (not (== (curRoom script?) enterBar))
					(curRoom setScript: enterBar)
				)
			)
			(
				(and
					(> (Random 0 100) 85)
					(== (ulence status?) 2)
					(ego inRect: 116 154 146 164)
				)
				(HandsOff)
				(ulence status: 3)
				((ScriptID ULENCE 1)
					view: 633
					init:
					hide:
					posn: -30 127
					setLoop: 0
					setScript: runOverScript1
				)
				(ego setScript: (ScriptID ULENCE 5))
			)
			(
				(and
					(> (Random 0 100) 85)
					(== (ulence status?) 2)
					(ego inRect: 196 175 222 185)
				)
				(HandsOff)
				(ulence status: 3)
				((ScriptID ULENCE 1)
					view: 632
					init:
					hide:
					posn: -28 (- (ego y?) 5)
					setLoop: 0
					setStep: 15 15
					setScript: runOverScript2
				)
				(ego setScript: (ScriptID ULENCE 5))
			)
			(
				(and
					(> (Random 0 100) 85)
					(== (ulence status?) 2)
					(ego inRect: 100 143 130 152)
				)
				(HandsOff)
				(ulence status: 3)
				((ScriptID ULENCE 1)
					view: 634
					init:
					hide:
					posn: 347 176
					setLoop: 1
					setScript: runOverScript3
				)
				(ego setScript: (ScriptID ULENCE 4))
			)
		)
		(super doit: &rest)
	)
)

(instance flyOut of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(music stop: number: 127 vol: 127)
				(= cycles 2)
			)
			(1
				(thumpSound vol: 127 loop: 1 number: 836 play:)
				(= ticks 40)
			)
			(2
				(ego
					z: 0
					setLoop: 0
					cel: 0
					setCycle: CycleTo 3 1
					setMotion: MoveTo 130 127 self
				)
			)
			(3
				(thumpSound number: 134 play:)
				(= seconds 2)
			)
			(4 (ego setCycle: EndLoop self))
			(5
				(ego
					view: 0
					setLoop: 1
					heading: 270
					posn: (- (ego x?) 25) (+ (ego y?) 25)
				)
				(= cycles 1)
			)
			(6
				(NormalEgo (ego loop?) 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance bikesFall of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(music loop: 1 number: 140 play:)
				(bike1 setCycle: EndLoop self)
			)
			(1 (bike2 setCycle: EndLoop self))
			(2 (bike3 setCycle: EndLoop self))
			(3 (self dispose:))
		)
	)
)

(instance exitBar of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 150 135 self)
			)
			(1
				(NormalEgo (ego loop?) 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance bikerScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				((ScriptID ULENCE 1)
					setLoop: 0
					setCycle: Forward
					setStep: 4 4
					setMotion: MoveTo 159 134 self
				)
			)
			(1
				((ScriptID ULENCE 1) setMotion: MoveTo 133 146 self)
			)
			(2
				((ScriptID ULENCE 1)
					setLoop: 1
					cel: 0
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(3 (= seconds 2))
			(4
				(biker2 setScript: biker2Script)
				((ScriptID ULENCE 1)
					setPri: 12
					setLoop: 2
					cel: 0
					posn: 121 147
					cycleSpeed: 3
					setCycle: EndLoop self
					setMotion: MoveTo 133 156
				)
				(= cycles 3)
			)
			(5
				(bike1Sound init: play:)
				(bike2Sound init:)
				(bike3Sound init:)
				(bike1 setCel: 0)
			)
			(6
				(bike1 dispose:)
				((ScriptID ULENCE 1)
					view: 634
					setLoop: 1
					setStep: 10 10
					setMotion: MoveTo -50 (- (ego y?) 40) self
				)
			)
			(7 (self dispose:))
		)
	)
)

(instance biker2Script of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(biker2
					view: 630
					cycleSpeed: 6
					setLoop: 0
					posn: 213 133
					setCycle: Forward
					setStep: 4 4
					setPri: 9
					setMotion: MoveTo 167 133 self
				)
			)
			(1
				(biker2 setMotion: MoveTo 134 162 self)
			)
			(2
				(biker2
					setLoop: 1
					cycleSpeed: 12
					cel: 0
					posn: 138 163
					setCycle: EndLoop self
				)
			)
			(3
				(biker2 setPri: 12)
				(= seconds 2)
			)
			(4
				(biker3 setScript: biker3Script)
				(biker2 setLoop: 2 setCel: 0 posn: 146 162)
				(= cycles 2)
			)
			(5
				(biker2 setCel: 1 posn: 149 162)
				(= cycles 2)
			)
			(6
				(biker2 setCel: 2 posn: 155 162)
				(= cycles 2)
			)
			(7
				(biker2 setCel: 3 posn: 164 172)
				(= cycles 2)
			)
			(8
				(biker2 setCel: 4 posn: 176 172)
				(= cycles 2)
			)
			(9
				(biker2 setCel: 5 posn: 176 178)
				(bike2 setCel: 0)
				(= cycles 2)
			)
			(10
				(bike2Sound play:)
				(bike2 dispose:)
				(biker2
					view: 633
					setLoop: 7
					setStep: 10 10
					setPri: -1
					setMotion: MoveTo 44 83 self
				)
			)
			(11
				(biker2 hide:)
				(self dispose:)
			)
		)
	)
)

(instance biker3Script of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(biker3
					view: 631
					posn: 217 133
					setLoop: 0
					setPri: 9
					cycleSpeed: 10
					setCycle: Forward
					setMotion: MoveTo 161 133 self
				)
			)
			(1
				(biker3 setMotion: MoveTo 133 161 self)
			)
			(2
				(biker3
					setLoop: 1
					posn: 132 163
					cel: 0
					cycleSpeed: 13
					setCycle: EndLoop self
				)
			)
			(3
				(biker3 setPri: 12)
				(= seconds 2)
			)
			(4
				(biker3 setLoop: 2 setCel: 0 posn: 145 167)
				(= cycles 3)
			)
			(5
				(biker3 setCel: 1 posn: 168 168)
				(= cycles 3)
			)
			(6
				(biker3 setCel: 2 posn: 176 170)
				(= cycles 3)
			)
			(7
				(biker3 setCel: 3 posn: 194 170)
				(= cycles 3)
			)
			(8
				(bike3Sound play:)
				(biker3
					view: 632
					setLoop: 5
					posn: 205 173
					setStep: 10 10
					setMotion: MoveTo 126 225 self
				)
				(bike3 dispose:)
			)
			(9 (= seconds 2))
			(10 (curRoom newRoom: 609))
		)
	)
)

(instance enterBar of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 214 135 self)
			)
			(1
				(if (or (Btst fKickBikes) (not (Btst fMetBikers)))
					(globalSound fade: 85 10 10 0)
					(curRoom newRoom: 615)
				else
					(ego
						view: 615
						setLoop: 0
						setCel: 3
						setPri: 9
						posn: 227 114
					)
					(= seconds 2)
				)
			)
			(2
				(thumpSound vol: 127 loop: 1 number: 836 play:)
				(ego posn: 205 104)
				(= cycles 1)
			)
			(3
				(ego posn: 188 95)
				(= cycles 1)
			)
			(4
				(ego posn: 160 87)
				(= cycles 1)
			)
			(5
				(ego posn: 146 88)
				(= cycles 1)
			)
			(6
				(ego posn: 128 99)
				(= cycles 1)
			)
			(7
				(ego posn: 116 112)
				(= cycles 1)
			)
			(8
				(ego posn: 88 123)
				(thumpSound number: 147 play:)
				(= seconds 2)
			)
			(9 (ego setCycle: EndLoop self))
			(10
				(ego
					view: 0
					setLoop: 1
					setHeading: 270
					posn: (- (ego x?) 25) (+ (ego y?) 25)
				)
				(= cycles 1)
			)
			(11
				(NormalEgo (ego loop?) 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance runOverScript1 of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(ulence egoBusy: TRUE)
				(globalSound
					vol: 25
					number: 50
					loop: -1
					playBed:
					fade: 127 10 10 0
				)
				(= seconds 2)
			)
			(1
				((ScriptID ULENCE 6) init: play:)
				((ScriptID ULENCE 1) show: setMotion: MoveTo 40 127 self)
			)
			(2
				(ulence status: 4)
				(HandsOn)
				((ScriptID ULENCE 1)
					setLoop: 4
					posn: 57 129
					setPri: (+ (ego priority?) 1)
					setMotion: MoveTo 192 174 self
				)
			)
			(3
				((ScriptID ULENCE 1)
					setLoop: 0
					posn: 209 177
					setMotion: MoveTo 369 177 self
				)
			)
			(4
				(globalSound fade: 0 10 10 0)
				((ScriptID ULENCE 6) fade:)
				(= seconds 3)
			)
			(5
				(globalSound vol: 70 number: 804 loop: -1 playBed:)
				((ScriptID ULENCE 1) hide:)
				(= seconds 3)
			)
			(6
				(if (!= (ulence status?) 6)
					(ulence status: 1 fieldOff: FALSE bikerComing: FALSE egoBusy: FALSE)
					(ego setScript: 0)
					(narrator modNum: 611 say: 1 self)
				)
				(= cycles 2)
			)
			(7 (HandsOn) (self dispose:))
		)
	)
)

(instance runOverScript2 of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(ulence egoBusy: TRUE)
				(globalSound
					vol: 25
					number: 50
					loop: -1
					playBed:
					fade: 127 10 10 0
				)
				(= seconds 2)
			)
			(1
				((ScriptID ULENCE 6) init: play:)
				((ScriptID ULENCE 1)
					show:
					setMotion: MoveTo 132 (- (ego y?) 7) self
				)
			)
			(2
				(ulence status: 4)
				(HandsOn)
				((ScriptID ULENCE 1)
					setPri: (+ (ego priority?) 1)
					setMotion: MoveTo 372 (- (ego y?) 7) self
				)
			)
			(3
				(globalSound fade: 0 10 10 0)
				((ScriptID ULENCE 6) fade:)
				(= seconds 3)
			)
			(4
				(globalSound vol: 70 number: 804 loop: -1 playBed:)
				((ScriptID ULENCE 1) hide:)
				(= seconds 3)
			)
			(5
				(if (!= (ulence status?) 6)
					(ulence status: 1 fieldOff: FALSE bikerComing: FALSE egoBusy: FALSE)
					(ego setScript: 0)
					(narrator modNum: 611 say: 1 self)
				)
				(= cycles 2)
			)
			(6 (HandsOn) (self dispose:))
		)
	)
)

(instance runOverScript3 of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(ulence egoBusy: TRUE)
				(globalSound
					vol: 25
					number: 50
					loop: -1
					playBed:
					fade: 127 10 10 0
				)
				(= seconds 2)
			)
			(1
				((ScriptID ULENCE 6) init: play:)
				((ScriptID ULENCE 1)
					show:
					setPri: 15
					setMotion: MoveTo 167 176 self
				)
			)
			(2
				(ulence status: 4)
				(HandsOn)
				((ScriptID ULENCE 1)
					setPri: (+ (ego priority?) 1)
					setLoop: 7
					setMotion: MoveTo 60 106 self
				)
			)
			(3
				((ScriptID ULENCE 1)
					setPri: -1
					setMotion: MoveTo 33 91 self
				)
			)
			(4
				((ScriptID ULENCE 1) hide:)
				(globalSound fade: 0 10 10 0)
				((ScriptID ULENCE 6) init: play:)
				(= seconds 3)
			)
			(5
				(globalSound vol: 70 number: 804 loop: -1 playBed:)
				(if (!= (ulence status?) 6)
					(ulence status: 1 fieldOff: FALSE bikerComing: FALSE egoBusy: FALSE)
					(ego setScript: 0)
					(narrator modNum: 611 say: 1 self)
				)
				(= cycles 2)
			)
			(6 (HandsOn) (self dispose:))
		)
	)
)

(instance kickScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(ulence status: 99)
				(LoadMany VIEW 615 616 632 633 634 50)
				(SolvePuzzle fKickBikes 5)
				(ego setMotion: PolyPath 130 169 self)
			)
			(1 (ego setHeading: 90 self))
			(2
				(ego view: 615 setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(3
				(self setScript: bikesFall self)
			)
			(4 (ego setCycle: BegLoop self))
			(5
				(NormalEgo (ego loop?) 0)
				(ego posn: (- (ego x?) 2) (ego y?) setLoop: 0)
				(= cycles 1)
			)
			(6
				(ego setLoop: -1 setHeading: 270 self)
			)
			(7
				(ulence fieldOff: TRUE)
				(ego
					view: 615
					setLoop: 5
					setCycle: Walk
					setStep: 6 4
					setMotion: MoveTo 25 (+ (ego y?) 20) self
				)
			)
			(8
				(music number: 0 vol: 0 stop:)
				(globalSound vol: 127 number: 50 loop: -1 playBed:)
				(= seconds 1)
			)
			(9
				((ScriptID ULENCE 1)
					init:
					view: 616
					posn: 210 134
					setScript: bikerScript
				)
				(ego setMotion: MoveTo -30 (+ (ego y?) 20) self)
			)
			(10
				(ulence fieldOff: 0)
				(ego dispose:)
			)
		)
	)
)

(instance biker2 of Sq4Actor
	(properties
		x 223
		y 134
		yStep 4
		view 630
		priority 9
		signal (| ignrAct ignrHrz fixPriOn stopUpdOn)
		illegalBits $0000
		xStep 4
	)
)

(instance biker3 of Sq4Actor
	(properties
		x 223
		y 134
		yStep 4
		view 631
		priority 9
		signal (| ignrAct ignrHrz fixPriOn stopUpdOn)
		illegalBits $0000
		xStep 4
	)
)

(instance theSign of Sq4Prop
	(properties
		x 209
		y 75
		sightAngle 45
		view 610
		priority 12
		signal (| ignrAct ignrHrz fixPriOn)
		lookStr 1
	)
)

(instance bike1 of Sq4Prop
	(properties
		x 159
		y 150
		view 615
		loop 4
		priority 12
		signal (| ignrAct fixPriOn stopUpdOn)
		lookStr 2
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_LOOK (narrator say: 2))
			(V_DO
				(cond 
					((not (Btst fMetBikers)) (narrator say: 3))
					((not (Btst fKickBikes)) (curRoom setScript: kickScript))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bike2 of Sq4Prop
	(properties
		x 187
		y 160
		view 615
		loop 3
		priority 13
		signal (| ignrAct fixPriOn stopUpdOn)
		lookStr 2
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_LOOK (narrator say: 2))
			(V_DO
				(cond 
					((not (Btst fMetBikers)) (narrator say: 3))
					((not (Btst fKickBikes)) (curRoom setScript: kickScript))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bike3 of Sq4Prop
	(properties
		x 211
		y 168
		view 615
		loop 2
		priority 14
		signal (| ignrAct fixPriOn stopUpdOn)
		lookStr 2
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_LOOK (narrator say: 2))
			(V_DO
				(cond 
					((not (Btst fMetBikers)) (narrator say: 3))
					((not (Btst fKickBikes)) (curRoom setScript: kickScript))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance ship1 of Sq4Feature
	(properties
		x 34
		y 88
		nsTop 49
		nsBottom 128
		nsRight 69
		sightAngle 45
		onMeCheck (| NEARCHECK ISNOTHIDDEN)
		lookStr 4
	)
)

(instance ship3 of Sq4Feature
	(properties
		x 150
		y 85
		nsTop 52
		nsLeft 105
		nsBottom 118
		nsRight 196
		sightAngle 45
		onMeCheck ISNOTHIDDEN
		lookStr 5
	)
)

(instance door of Sq4Feature
	(properties
		x 178
		y 111
		nsTop 85
		nsLeft 162
		nsBottom 138
		nsRight 194
		sightAngle 45
		onMeCheck FARCHECK
		lookStr 6
	)
	
	(method (doVerb theVerb param2)
		(switch theVerb
			(V_LOOK (narrator say: 6))
			(V_DO
				(if (not (== (curRoom script?) enterBar))
					(curRoom setScript: enterBar)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance building of Sq4Feature
	(properties
		x 225
		y 93
		nsTop 23
		nsLeft 132
		nsBottom 164
		nsRight 319
		sightAngle 45
		onMeCheck NEARCHECK
		lookStr 1
	)
)

(instance thumpSound of Sound
	(properties
		flags mNOPAUSE
		number 147
	)
)

(instance bike1Sound of Sound
	(properties
		flags mNOPAUSE
		number 837
	)
)

(instance bike2Sound of Sound
	(properties
		flags mNOPAUSE
		number 837
	)
)

(instance bike3Sound of Sound
	(properties
		flags mNOPAUSE
		number 837
	)
)
