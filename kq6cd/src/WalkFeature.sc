;;; Sierra Script 1.0 - (do not remove this comment)
(script# 300)
(include sci.sh)
(use Main)
(use rCliffs)
(use KQ6Print)
(use n301)
(use Kq6Procs)
(use Conv)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm300 0
	stepDownToBeach 1
)

(local
	[local0 37] = [0 0 0 0 0 0 0 0 0 0 0 0 0 -3 108 142 200 219 170 156 170 166 188 46 53 90 25 258 223 245 122 112 106 111 113 109 117]
	[local37 28] = [1 1 1 1 1 1 1 1 1 2 2 2 2 2 3 3 3 3 3 3 4 4 4 4 4 4 4 4]
	[local65 28] = [0 1 2 3 4 5 6 7 8 0 1 2 3 4 0 1 2 3 4 5 0 1 2 3 4 5 6 7]
	[local93 28] = [0 0 0 0 1 0 0 0 0 0 2 0 0 0 0 0 3 0 0 0 4 0 4 0 0 0 4]
	[local121 28] = [105 111 124 137 152 166 177 191 204 134 146 153 163 172 115 134 141 153 166 181 116 126 135 146 158 170 180 190]
	[local149 28] = [56 56 56 56 56 56 56 56 56 73 73 73 73 73 89 89 89 89 89 89 105 105 105 105 105 105 105 105]
	[local177 28] = [49 49 49 49 49 49 49 49 49 66 66 66 66 66 82 82 82 82 82 82 98 98 98 98 98 98 98 98]
	[local205 28] = [102 108 121 134 149 163 174 188 201 131 143 150 160 169 112 131 138 150 163 178 113 123 132 143 155 167 177 187]
	[local233 28] = [108 121 134 149 163 174 188 201 214 143 150 160 169 183 131 138 150 163 178 188 123 132 143 155 167 177 187 200]
	[local261 28] = [65 65 65 65 65 65 65 65 65 81 81 81 81 81 98 98 98 98 98 98 113 113 113 113 113 113 113 113]
	[local289 34] = [0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 18 1 2 6]
	local323
	local324
	local325
	local326
	local327
)
(class WalkFeature of Feature
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
	)
	
	(method (init)
		(super init: &rest)
		(walkHandler add: self)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp userCurEvent)
		(= userCurEvent (user curEvent?))
		(if (== theVerb 3)
			(cond 
				(
				(and (== (ego view?) 301) (== (ego loop?) 3)) 0)
				((< (ego y?) 38) (curRoom setScript: splatterDie 0 1))
				((< (ego y?) 95) (curRoom setScript: splatterDie 0 0))
				((<= (ego y?) 105)
					((ScriptID 21 0) cue:)
					(curRoom setScript: stepDownToBeach)
				)
				(
				(OneOf (if (== (ego view?) 301) (== 301 301))) 0)
				(else
					(if
						(and
							(> (userCurEvent y?) 160)
							(== (ego onControl: 1) 64)
						)
						(userCurEvent y: 160)
					)
					(ego setScript: walkThere 0 userCurEvent)
				)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance rm300 of CliffRoom
	(properties
		noun 8
		picture 300
		horizon 0
		walkOffEdge 1
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						319
						189
						253
						189
						241
						181
						238
						175
						233
						159
						230
						149
						223
						114
						211
						113
						209
						111
						190
						116
						103
						115
						88
						124
						73
						122
						72
						125
						66
						135
						70
						155
						90
						189
						6
						189
						0
						189
						0
						0
						319
						0
					yourself:
				)
		)
		(if (!= prevRoomNum 380)
			(theMusic number: 915 setLoop: -1 play:)
		)
		(soundFx2 number: 916 setLoop: -1 play:)
		(if (Btst 157)
			(self north: 340)
		else
			(self north: 320)
		)
		(if (== prevRoomNum 320)
			(self style: 13)
		else
			(self style: -32758)
		)
		(super init:)
		(features
			add: cliffs ocean rock stone beach
			eachElementDo: #init
		)
		(if (Btst 5) (self allRocksOut:))
		(LoadMany 128 320 321 308 3081 3082)
		(wave init: hide: setScript: waveScr)
		(sanScript init:)
		(shimmer1 init:)
		(shimmer2 init:)
		(if (== ((inventory at: 12) owner?) curRoomNum)
			(feather init: stopUpd:)
		)
		(if (== ((inventory at: 13) owner?) curRoomNum)
			(stench init: stopUpd:)
		)
		(ego actions: egoDoVerb)
		(cond 
			((OneOf prevRoomNum 370 380) (ego init: reset: posn: 340 -10) (proc301_0))
			((OneOf prevRoomNum 320 340)
				(if (== prevRoomNum 320) (UnLoad 128 322))
				(Load rsVIEW 321)
				(ego view: 301 setLoop: 1 cel: 0 init: posn: 70 4)
				(curRoom setScript: stepDownFromCliff)
			)
		)
	)
	
	(method (doit)
		(cond 
			(
				(and
					(== (ego onControl: 1) 64)
					(== (ego view?) 308)
					(!= (ego view?) 900)
				)
				(= local325 0)
				(theGlobalSound fade: 0 10 15 1)
				(ego view: 900)
			)
			(
				(and
					(== (ego onControl: 1) 32)
					(or (== (ego view?) 900) (== (ego view?) 3081))
					(!= (ego view?) 308)
				)
				(theGlobalSound number: 922 setLoop: -1 play:)
				(cond 
					((!= (ego view?) 900) 0)
					((< (ego heading?) 135) (ego loop: 0))
					((> (ego heading?) 225) (ego loop: 1))
					(else (ego loop: 2))
				)
				(ego view: 308)
			)
			(
				(and
					(or
						(== (ego onControl: 1) 512)
						(== (ego onControl: 1) 1024)
					)
					(!= (ego view?) 3081)
				)
				(ego view: 3081)
			)
			(
				(and
					(or
						(== (ego onControl: 1) 4096)
						(== (ego onControl: 1) 2048)
					)
					(!= (ego view?) 3082)
					(not local323)
				)
				(ego view: 3082)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 301)
		(super dispose:)
	)
	
	(method (cue)
		(cond 
			((< (ego y?) 38) (curRoom setScript: splatterDie 0 1))
			((< (ego y?) 95) (curRoom setScript: splatterDie 0 0))
		)
	)
	
	(method (newRoom n)
		(if (!= n 320)
			(theMusic fade: 0 5 5)
			(soundFx2 fade: 0 5 5)
		)
		(super newRoom: n)
	)
	
	(method (notify)
		(curRoom setScript: rockStair)
	)
	
	(method (makeARock)
		(= local324 1)
		(super makeARock:)
	)
)

(instance myConv of Conversation
	(properties)
)

(instance puzzle of PuzzleInset
	(properties
		x 157
		y 39
		z -45
		view 320
		signal $4000
		maxButtons 4
		buttNumber 28
		buttView 321
		lookMsg 8
		puzzNumber 1
	)
	
	(method (init)
		(self
			buttTop: @local177
			buttLeft: @local205
			buttRight: @local233
			buttBottom: @local261
			buttLoop: @local37
			buttCel: @local65
			buttX: @local121
			buttY: @local149
			buttVal: @local93
			buttKill: @local289
		)
		(ego hide: view: 300)
		(UnLoad 128 900)
		(words init:)
		(super init:)
	)
	
	(method (dispose)
		(words dispose:)
		(ego view: 900 loop: 3 show:)
		(super dispose:)
	)
)

(instance words of View
	(properties
		x 209
		y 71
		z -40
		view 321
		priority 13
		signal $4010
	)
	
	(method (doVerb theVerb)
		(puzzle doVerb: theVerb &rest)
	)
)

(instance stench of View
	(properties
		x 68
		y 127
		noun 12
		view 300
		loop 4
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(cond 
				((< (ego y?) 105) (messager say: 6 12 14 1))
				((== ((inventory at: 13) owner?) curRoomNum) (curRoom setScript: pickItem 0 self))
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance feather of View
	(properties
		x 210
		y 128
		noun 11
		view 310
		loop 4
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(if (< (ego y?) 105)
				(messager say: 6 12 14 1)
			else
				(curRoom setScript: pickItem 0 self)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance wave of Prop
	(properties
		x 162
		y 159
		noun 7
		view 300
		priority 1
		signal $0010
	)
)

(instance shimmer1 of Prop
	(properties
		x 24
		y 177
		noun 7
		view 300
		loop 1
		priority 1
		signal $4010
		cycleSpeed 30
		detailLevel 3
	)
	
	(method (init)
		(self setCycle: Fwd)
		(super init:)
	)
)

(instance shimmer2 of Prop
	(properties
		x 261
		y 182
		noun 7
		view 300
		loop 2
		priority 1
		signal $4010
		cycleSpeed 30
		detailLevel 3
	)
	
	(method (init)
		(self setCycle: Fwd)
		(super init:)
	)
)

(instance sanScript of View
	(properties
		x 138
		y 189
		z 124
		noun 3
		sightAngle 180
		view 326
		signal $4010
	)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler add: self)
		(mouseDownHandler add: self)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(User canInput:)
				(!= (event type?) evVERB)
				(not (event modifiers?))
				(self onMe: event)
				(not ((ScriptID 21 0) puzzleIsUp?))
				(or
					(== (theIconBar curIcon?) (theIconBar at: 1))
					(== (theIconBar curIcon?) (theIconBar at: 2))
				)
				(or
					(== (event message?) KEY_RETURN)
					(== (event type?) evMOUSEBUTTON)
				)
				(User canInput:)
				(!= (event type?) evVERB)
				(not (event modifiers?))
			)
			(event claimed: 1)
			(cond 
				((<= (ego y?) 105)
					(if modelessDialog
						(if modelessDialog (modelessDialog dispose:))
					else
						(messager say: 3 1 14 1)
					)
				)
				((or (Btst 6) (Btst 5)) (puzzle puzzSolved:))
				(else (ego setScript: goToInset))
			)
		else
			(super handleEvent: event)
		)
	)
)

(instance stone of WalkFeature
	(properties
		noun 9
		onMeCheck $0100
	)
)

(instance rock of WalkFeature
	(properties
		noun 9
		onMeCheck $0c00
	)
)

(instance ocean of WalkFeature
	(properties
		noun 7
		onMeCheck $1220
	)
)

(instance cliffs of WalkFeature
	(properties
		noun 8
		onMeCheck $0080
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(1
				(if local324
					(messager say: 8 1 22 0)
				else
					(messager say: 8 1 5 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance beach of Feature
	(properties
		noun 2
		onMeCheck $0040
	)
	
	(method (init)
		(super init: &rest)
		(walkHandler add: self)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp userCurEvent)
		(= userCurEvent (user curEvent?))
		(switch theVerb
			(3
				(cond 
					(
					(and (== (ego view?) 301) (== (ego loop?) 3)) 0)
					((< (ego y?) 38) (curRoom setScript: splatterDie 0 1))
					((< (ego y?) 95) (curRoom setScript: splatterDie 0 0))
					((<= (ego y?) 105)
						((ScriptID 21 0) cue:)
						(curRoom setScript: stepDownToBeach)
					)
					(
					(or (== (ego view?) 301) (== (ego view?) 301)) 0)
					(else
						(if
							(and
								(> (userCurEvent y?) 160)
								(== (ego onControl: 1) 64)
							)
							(userCurEvent y: 160)
						)
						(ego setScript: walkThere 0 userCurEvent)
					)
				)
			)
			(1
				(if (Btst 5)
					(messager say: 2 1 22 1)
				else
					(messager say: 2 1 5 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance waveScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (> (theGame detailLevel:) (wave detailLevel:))
					(wave show: cel: 0 setCycle: End self)
				else
					(= state (+ state 1))
					(self cue:)
				)
			)
			(1 (wave setCycle: Beg self))
			(2
				(= state -1)
				(wave hide:)
				(= seconds (Random 1 7))
			)
		)
	)
)

(instance walkThere of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(or
						(== (ego onControl: 1) 32)
						(== (ego onControl: 1) 512)
						(== (ego onControl: 1) 1024)
					)
					(= local325 1)
				else
					(= local325 0)
				)
				(ego
					setMotion: PolyPath (register x?) (register y?) self
				)
			)
			(1 (= cycles 2))
			(2
				(cond 
					(
						(and
							(not local325)
							(or
								(== (ego onControl: 1) 32)
								(== (ego onControl: 1) 512)
								(== (ego onControl: 1) 1024)
							)
						)
						(messager say: 7 3 24 1 self)
					)
					(
						(or
							(== (ego onControl: 1) 4096)
							(== (ego onControl: 1) 2048)
						)
						(myConv add: -1 7 3 26 1 add: -1 7 3 26 2 init: self)
					)
					(else (self cue:))
				)
			)
			(3
				(if
					(or
						(== (ego onControl: 1) 4096)
						(== (ego onControl: 1) 2048)
					)
					(self cue:)
				else
					(self dispose:)
				)
			)
			(4
				(theGame handsOff:)
				(= local323 1)
				(theGlobalSound number: 921 setLoop: 1 play:)
				(ego
					view: 309
					cel: 0
					normal: 0
					cycleSpeed: 6
					setCycle: End self
				)
			)
			(5 (curRoom newRoom: 135))
		)
	)
)

(instance splatterDie of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(rCliffs cheatCount: 0)
				(if (== register 0) (Load rsVIEW 201))
				((ScriptID 21 0) cue:)
				(messager say: 4 3 8 1 self)
			)
			(1
				(if (< (ego loop?) 6)
					(ego
						setLoop: 4
						posn: (- (ego x?) 19) (+ (ego y?) 6)
						cel: 2
					)
					(= ticks 2)
				else
					(self cue:)
				)
			)
			(2
				(soundFx number: 305 setLoop: 1 play:)
				(if (== (ego loop?) 6)
					(ego
						view: 3011
						posn: (+ (ego x?) 18) (+ (ego y?) 10)
						cycleSpeed: 6
						setLoop: 1
					)
				else
					(ego
						view: 301
						posn: (ego x?) (+ (ego y?) 2)
						cycleSpeed: 6
						setLoop: 3
					)
				)
				(ego cel: 0 setCycle: End self)
			)
			(3
				(if (== (ego view?) 301)
					(ego setCycle: Beg self)
				else
					(self cue:)
				)
			)
			(4
				(if (== (ego view?) 301)
					(ego
						posn: (+ (ego x?) 18) (- (ego y?) 7)
						setLoop: (Random 1 2)
					)
				else
					(ego posn: (- (ego x?) 18) (- (ego y?) 10) setLoop: 6)
				)
				(ego view: 301 cycleSpeed: 6 cel: 0)
				(= cycles 10)
			)
			(5
				(if (== (ego loop?) 4)
					(ego posn: (- (ego x?) 1) (- (ego y?) 9))
				else
					(ego posn: (- (ego x?) 1) (- (ego y?) 3))
				)
				(ego view: 307 cycleSpeed: 1 setLoop: 0)
				(Bset 59)
				(if (& msgType $0002)
					(KQ6Print modeless: 1 posn: -1 10 say: 0 4 3 8 2 init:)
				)
				(= cycles 4)
			)
			(6 (ego setCycle: End self))
			(7
				(Bclr 59)
				(soundFx number: 306 setLoop: 1 play:)
				(ego setLoop: 1 yStep: 35)
				(if (== register 0) (= temp0 15) else (= temp0 0))
				(ego
					setStep: 10 12
					setMotion: PolyPath (+ (ego x?) temp0) 116 self
				)
			)
			(8
				(if (== register 0)
					(self setScript: bounceButt self)
				else
					(ego setLoop: 2 setCycle: End self)
				)
				(soundFx stop:)
				(theGlobalSound number: 307 setLoop: 1 play:)
			)
			(9
				(if (== register 0)
					(ego
						setLoop: 4
						cel: 0
						posn: (- (ego x?) 55) (+ (ego y?) 19)
						setCycle: End self
					)
				else
					(messager say: 4 3 9 1 self)
				)
			)
			(10
				(if (== register 0)
					(ego posn: (+ (ego x?) 33) (+ (ego y?) 4) reset: 5)
					(= cycles 8)
				else
					(self cue:)
				)
			)
			(11
				(if (== register 0)
					(if (== local326 3)
						(messager say: 1 0 15 1 self)
					else
						(messager say: 4 3 8 3 self)
					)
				else
					(self cue:)
				)
			)
			(12
				(if (== register 0)
					(theGame handsOn:)
					(ego actions: egoDoVerb)
					(self dispose:)
				else
					(soundFx2 fade: 0 5 5)
					(EgoDead 8)
				)
			)
		)
	)
)

(instance bounceButt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(++ local326)
				(ego
					view: 307
					setLoop: 3
					cel: 0
					normal: 0
					setSpeed: 6
					posn: (ego x?) (ego y?)
				)
				(= ticks 4)
			)
			(1
				(ego cel: 1 posn: (+ (ego x?) 4) (- (ego y?) 1))
				(= ticks 4)
			)
			(2
				(ego cel: 2 posn: (ego x?) (- (ego y?) 1))
				(= ticks 4)
			)
			(3
				(ego cel: 3 posn: (ego x?) (- (ego y?) 5))
				(= ticks 4)
			)
			(4
				(ego cel: 4 posn: (ego x?) (- (ego y?) 1))
				(= ticks 4)
			)
			(5
				(ego cel: 5 posn: (ego x?) (- (ego y?) 1))
				(= ticks 4)
			)
			(6
				(ego cel: 6 posn: (ego x?) (- (ego y?) 1))
				(= ticks 4)
			)
			(7
				(directionHandler delete: curRoom)
				(self dispose:)
			)
		)
	)
)

(instance stepDownFromCliff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rCliffs cue: cheatCount: 8 stepDirection: 1)
				(ego
					view: 301
					setLoop: 8
					cel: 0
					cycleSpeed: 16
					posn: (+ (ego x?) 1) (ego y?)
				)
				(= cycles 8)
			)
			(1
				(if (== local327 0)
					(if (== prevRoomNum 340)
						(messager say: 1 0 6 1 self)
					else
						(= cycles 1)
					)
				else
					(self cue:)
				)
			)
			(2
				(proc21_1)
				(ego cel: 1 posn: (ego x?) (ego y?))
				(= cycles 8)
			)
			(3
				(ego cel: 2 posn: (ego x?) (ego y?))
				(= cycles 8)
			)
			(4
				(ego cel: 3 posn: (ego x?) (ego y?))
				(= cycles 8)
			)
			(5
				(ego cel: 4 posn: (+ (ego x?) 1) (ego y?))
				(= cycles 8)
			)
			(6
				(ego cel: 5 posn: (- (ego x?) 1) (ego y?))
				(= cycles 8)
			)
			(7
				(ego cel: 6 posn: (+ (ego x?) 2) (ego y?))
				(= cycles 8)
			)
			(8
				(if (== local327 3)
					(self cue:)
				else
					(ego
						view: 301
						setLoop: 8
						cel: 0
						posn: (+ (ego x?) 27) (+ (ego y?) 12)
					)
					(= cycles 8)
				)
			)
			(9
				(if (< local327 3)
					(++ local327)
					(= state (- state 9))
				else
					(= local327 0)
					(ego setLoop: 2 cel: 0 posn: 188 50)
				)
				(self cue:)
			)
			(10
				(theGame handsOn:)
				((ScriptID 21 0) notify:)
				(self dispose:)
			)
		)
	)
)

(instance stepDownToBeach of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== (rCliffs stepDirection?) 2)
					(ego
						view: 301
						setLoop: 5
						cel: 0
						posn: (+ (ego x?) 18) (+ (ego y?) 7)
						setCycle: End self
					)
				else
					(self cue:)
				)
			)
			(1
				(if (== (rCliffs stepDirection?) 2)
					(rCliffs stepDirection: 3)
					(ego
						view: 301
						setLoop: 1
						cel: 0
						posn: (+ (ego x?) 16) (- (ego y?) 6)
					)
					(= cycles 8)
				else
					(self cue:)
				)
			)
			(2
				(ego
					view: 301
					x: (- (ego x?) 20)
					y: (+ (ego y?) 11)
					setLoop: 0
					cel: 5
					setCycle: Beg self
				)
			)
			(3
				(if (== prevRoomNum 340)
					(messager say: 1 0 6 1 self)
				else
					(self cue:)
				)
			)
			(4
				(ego posn: 110 112 actions: egoDoVerb reset: 3)
				(= cycles 8)
			)
			(5
				(ego setHeading: 180)
				(= cycles 14)
			)
			(6
				(theGame handsOn:)
				(directionHandler delete: curRoom)
				(self dispose:)
			)
		)
	)
)

(instance goToInset of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (Btst 5)
					(self cue:)
				else
					(messager say: 3 1 0 1 self)
				)
			)
			(1
				(if (ego inRect: 140 110 180 125)
					(self cue:)
				else
					(ego setMotion: PolyPath 146 127 self)
				)
			)
			(2
				(ego setHeading: 0)
				(= ticks 18)
			)
			(3
				(theGame handsOn:)
				(puzzle init:)
				(self dispose:)
			)
		)
	)
)

(instance pickItem of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== register stench)
					(ego
						setMotion: PolyPath (+ (register x?) 34) (+ (register y?) 2) self
					)
				else
					(ego
						setMotion: PolyPath (+ (register x?) 7) (- (register y?) 12) self
					)
				)
			)
			(1
				(if (== register stench)
					(ego setHeading: 315)
				else
					(ego setHeading: 180)
				)
				(= ticks 18)
			)
			(2
				(if (== register stench)
					(ego
						view: 302
						setLoop: 0
						normal: 0
						cel: 0
						setPri: (ego priority?)
						posn: 89 135
						cycleSpeed: 6
						setCycle: CT 2 1 self
					)
				else
					(ego
						view: 311
						setLoop: 0
						normal: 0
						cel: 0
						setPri: (ego priority?)
						posn: (- (ego x?) 2) (+ (ego y?) 11)
						cycleSpeed: 6
						setCycle: CT 3 1 self
					)
				)
				(theGame givePoints: 1)
			)
			(3
				(if (== register feather)
					(feather dispose:)
				else
					(stench dispose:)
				)
				(ego setCycle: End self)
			)
			(4
				(if (== register feather)
					(ego get: 12)
					(messager say: 11 5 0 1 self)
				else
					(ego get: 13)
					(messager say: 12 5 0 1 self)
				)
			)
			(5
				(if (== register stench)
					(ego reset: 7 posn: (+ (ego x?) 11) (- (ego y?) 5))
					(UnLoad 128 302)
				else
					(ego reset: 2 posn: (+ (ego x?) 2) (- (ego y?) 9))
					(UnLoad 128 311)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance rockStair of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 5)
				(= seconds 2)
			)
			(1
				(curRoom flipRocks: 0 callForRocks:)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance egoDoVerb of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 12)
				(if (== (ego onControl: 1) 64)
					(curRoom setScript: 130)
					(return 1)
				else
					(curRoom setScript: 130)
					(return 1)
				)
			else
				(return 0)
			)
		)
	)
)
