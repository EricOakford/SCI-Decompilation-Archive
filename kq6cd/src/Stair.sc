;;; Sierra Script 1.0 - (do not remove this comment)
(script# 800)
(include sci.sh)
(use Main)
(use CastleRoom)
(use Conv)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm800 0
	proc800_1 1
	roomConv 2
)

(local
	local0
	[local1 24] = [0 189 0 0 319 0 319 189 207 189 207 186 285 186 212 143 75 143 16 186 161 186 161 189]
	[local25 8] = [75 152 99 152 99 159 75 159]
	[local33 16] = [197 147 197 149 165 149 165 158 205 158 205 160 113 160 113 147]
	[local49 8] = [288 187 212 143 77 143 22 187]
	[local57 34] = [0 -10 319 -10 319 189 295 189 0 189 0 186 287 186 211 143 119 143 119 147 201 147 201 152 99 152 99 144 74 144 52 161 0 161]
	[local91 24] = [79 82 98 82 192 155 192 151 99 78 88 78 88 76 110 76 238 -10 231 -10 108 73 63 73]
	theNsLeft
	theCel
	[local117 8] = [0 0 198 155 79 77 211 5]
	[local125 6] = [188 185 188 189 110 189]
	local131
	local132
	local133
	local134
	local135
)
(procedure (proc800_1)
	(curRoom drawPic: (curRoom picture?))
	(if (== local0 3)
		(upHallleft addToPic:)
		(upHalleft2 addToPic:)
		(upStairs addToPic:)
		(upStairs2 addToPic:)
	else
		(downStairs addToPic:)
		(downUpperHalf addToPic:)
		(downFloor addToPic:)
		(backPost show: stopUpd:)
	)
	(stoneDoor show: stopUpd:)
	(beam show:)
	(chink show: stopUpd:)
	(ego show:)
	(localproc_0ca0)
)

(procedure (localproc_0ca0 param1)
	(if (== argc 1) (= local0 [param1 0]))
	(if (curRoom obstacles?)
		((curRoom obstacles?) dispose:)
		(curRoom obstacles: 0)
	)
	(mainPoly type: 2)
	(switch local0
		(1
			(curRoom
				addObstacle:
					(mainPoly type: 3 points: @local49 size: 4 yourself:)
					(Poly1 type: 2 points: @local25 size: 4 yourself:)
					(Poly2 type: 2 points: @local33 size: 8 yourself:)
			)
		)
		(2
			(backPost setPri: 0 stopUpd:)
			(curRoom
				addObstacle: (mainPoly type: 3 points: @local91 size: 12 yourself:)
			)
		)
		(3
			(curRoom
				addObstacle: (mainPoly points: @local57 size: 17 yourself:)
			)
		)
	)
)

(instance rm800 of CastleRoom
	(properties
		noun 3
		picture 800
		style $000a
		horizon 17
		west 810
	)
	
	(method (init)
		(switch prevRoomNum
			(810 (= local0 3))
			(else  (= local0 1))
		)
		(localproc_0ca0)
		(walls init:)
		(super init: &rest)
		(stoneDoor init:)
		(switch prevRoomNum
			(810
				(upHallleft addToPic:)
				(upHalleft2 addToPic:)
				(upStairs addToPic:)
				(upStairs2 addToPic:)
				(climbStairs register: 3)
				(ego posn: 30 165)
				(self setScript: firstTimeOnSecondLevel)
			)
			(else 
				(downStairs addToPic:)
				(downUpperHalf addToPic:)
				(downFloor addToPic:)
				(backPost init: stopUpd:)
				(= local131 2)
				(secretDoorMove register: 1)
				(stoneDoor posn: 110 189)
				(self setScript: secretDoorMove)
				(ego reset: 3 posn: 183 184)
				(if (not ((ScriptID 80 0) tstFlag: 709 32))
					((ScriptID 80 0) setFlag: 711 4)
					(if (not script)
						(self setScript: firstEntry)
					else
						(script next: firstEntry)
					)
				)
			)
		)
		(chink init: show: stopUpd:)
		(beam init: addToPic:)
		(ego init: reset:)
		(if (== prevRoomNum 810) (ego loop: 9 cel: 0))
		(ego setScale: 0 scaleX: 128 scaleY: 128 ignoreHorizon:)
		(if (not script) (theGame handsOn:))
		(if (!= (theMusic number?) 810)
			(theMusic fadeTo: 810 -1)
		)
	)
	
	(method (doit)
		(= local135 (ego onControl: 1))
		(cond 
			(script 0)
			((and (== local0 1) (& local135 $4000)) (self setScript: getOnStairs 0 1) (localproc_0ca0 2))
			(
				(and
					(== local0 3)
					(<= (ego y?) 149)
					(& local135 $1000)
				)
				(self setScript: changeLandings)
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: noun theVerb (+ 14 (== local0 3)))
				1
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance firstEntry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1 (messager say: 1 0 1 0 self))
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance firstTimeOnSecondLevel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 10))
			(1
				(if (not ((ScriptID 80 0) tstFlag: 711 8))
					((ScriptID 80 0) setFlag: 711 8)
					(roomConv add: -1 1 0 3)
				)
				(if (not ((ScriptID 80 0) tstFlag: 710 256))
					(roomConv add: -1 1 0 6)
				)
				(if (roomConv size?)
					(roomConv init: self)
				else
					(= cycles 1)
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance changeLandings of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch local0
					(2
						(backPost startUpd:)
						(ego setMotion: MoveTo 207 9 self)
					)
					(3
						(ego setPri: 1 setLoop: 1 setMotion: MoveTo 157 164 self)
					)
				)
			)
			(1
				(if (== local0 3)
					(localproc_0ca0 2)
				else
					(backPost dispose:)
					(chink startUpd:)
					(localproc_0ca0 3)
				)
				(addToPics eachElementDo: #dispose release:)
				(curRoom drawPic: 800 10)
				(switch local0
					(3
						(upHallleft addToPic:)
						(upHalleft2 addToPic:)
						(upStairs addToPic:)
						(upStairs2 addToPic:)
						(ego setPri: 1 posn: 157 164)
					)
					(else 
						(downStairs addToPic:)
						(downUpperHalf addToPic:)
						(downFloor addToPic:)
						(backPost init: stopUpd:)
						(ego posn: 207 9)
					)
				)
				(stoneDoor init: stopUpd:)
				(beam addToPic:)
				(chink show: stopUpd:)
				(= cycles 3)
			)
			(2
				(switch local0
					(3
						(= next firstTimeOnSecondLevel)
						(ego setMotion: MoveTo 191 145 self)
					)
					(else 
						(ego setLoop: -1)
						(= next (climbStairs register: 3 yourself:))
						(ego setMotion: MoveTo 168 34 self)
					)
				)
			)
			(3
				(if (!= local0 3)
					(if (not ((ScriptID 80 0) tstFlag: 709 32))
						(messager say: 1 0 1 0 self)
					else
						(= cycles 1)
					)
				else
					(= cycles 1)
				)
			)
			(4
				(if (!= local0 3) (theGame handsOn:))
				(ego reset:)
				(self dispose:)
			)
		)
	)
)

(instance getOnStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if register
					(ego setMotion: MoveTo 167 136 self)
				else
					(ego setMotion: MoveTo 198 155 self)
				)
			)
			(1
				(theGame handsOn:)
				(if register
					(client setScript: climbStairs)
				else
					(backPost setPri: -1 stopUpd:)
					(self dispose:)
				)
			)
		)
	)
)

(instance climbStairs of Script
	(properties
		register 1
	)
	
	(method (init)
		(directionHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(super init: &rest)
	)
	
	(method (doit)
		(cond 
			((and (== local0 2) (& local135 $1000)) (client setScript: getOnStairs 0 0) (localproc_0ca0 1))
			((== (ego edgeHit?) 1) (client setScript: changeLandings))
		)
		(super doit:)
	)
	
	(method (dispose)
		(directionHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (cue)
		(return 0)
	)
	
	(method (handleEvent event)
		(if
			(and
				(!= (event type?) evVERB)
				(not (event modifiers?))
			)
			(if (& (event type?) evMOUSEBUTTON) (= local134 1))
			(if (& (event type?) evJOYSTICK)
				(event claimed: 1)
				(if local134
					(= local134 0)
					(= register (+ 2 (- (* (<= (ego y?) 77) 2) 1)))
				)
				(cond 
					((OneOf (event message?) 1 5)
						(switch (event message?)
							(JOY_UP
								(if (< register 3) (++ register))
							)
							(JOY_DOWN
								(if (> register 1) (-- register))
							)
						)
						(ego
							setMotion:
								PolyPath
								[local117 (* register 2)]
								[local117 (+ (* register 2) 1)]
						)
					)
					((not (OneOf (event message?) 8 2 6 4)) (event claimed: 0))
				)
			)
		)
		(event claimed?)
	)
)

(instance secretDoorMove of Script
	(properties
		register -1
	)
	
	(method (init)
		(= register (- (* (== register -1) 2) 1))
		(if (== register -1)
			(curRoom south: 0)
			((curRoom obstacles?)
				delete: mainPoly
				add: (mainPoly type: 3 points: @local49 size: 4 yourself:)
			)
		else
			(theGame handsOff:)
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(if (== register 1)
			(curRoom south: 720)
			(= next exitRoom)
		)
		(if (not next) (theGame handsOn:))
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(soundFx2 number: 909 loop: 1 play:)
				(= ticks 1)
			)
			(2
				(if
					(and
						(<= 0 (= local131 (+ local131 register)))
						(<= local131 2)
					)
					(-- state)
					(= local132 [local125 (* local131 2)])
					(= local133 [local125 (+ (* local131 2) 1)])
					(stoneDoor
						moveSpeed: 4
						setMotion: MoveTo local132 local133 self
					)
				else
					(= cycles 1)
				)
			)
			(3
				(soundFx2 stop:)
				(stoneDoor stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance exitRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (ego x?) 200 self)
			)
			(1 (curRoom newRoom: 720))
		)
	)
)

(instance stoneDoor of Actor
	(properties
		x 188
		y 185
		noun 8
		view 801
		loop 2
		cel 4
		priority 14
		signal $4810
	)
	
	(method (init)
		(super init: &rest)
		(self
			approachX: 188
			approachY: 183
			approachVerbs: 5
			stopUpd:
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if (not script) (self setScript: secretDoorMove))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event)
		(return
			(if
			(and (!= local131 3) (!= local0 3) (super onMe: event))
				(> (event x?) 115)
			else
				0
			)
		)
	)
)

(instance chink of View
	(properties
		x 289
		y 128
		view 801
		loop 3
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5 1)
	)
	
	(method (doVerb theVerb)
		(cond 
			(
				(and
					(== (approachCode doit: theVerb) -32768)
					(not (= theVerb 0))
				)
				(messager say: 7 theVerb)
			)
			((OneOf theVerb 5 1)
				(switch noun
					(4
						(curRoom setScript: (ScriptID 801))
					)
					(else 
						(curRoom setScript: (ScriptID 802))
					)
				)
			)
			(else (super doVerb: theVerb))
		)
	)
	
	(method (onMe event)
		(if (super onMe: event)
			(cond 
				(
					(and
						(== local0 3)
						(= approachX 228)
						(= approachY 157)
						(= noun 4)
					)
				)
				((= approachX 258) (if (= approachY 166) (= noun 6)))
			)
		)
	)
	
	(method (show)
		(if (!= local0 3)
			(self posn: 289 128)
		else
			(self posn: 258 122)
		)
		(super show: &rest)
	)
)

(instance beam of View
	(properties
		onMeCheck $0000
		view 801
		loop 4
		signal $4010
	)
	
	(method (init)
		(= cel (- (NumCels self) 1))
		(super init: &rest)
	)
	
	(method (doit)
		(if
		(and (not (& signal $0008)) (!= (ego view?) 802))
			(if
				(and
					(>= (ego x?) theNsLeft)
					(>= (+ y 2) (ego y?))
					(>= (ego y?) (- y 2))
				)
				(= cel (- (/ (- x (ego x?)) 7) 1))
				(= priority (- (ego priority?) 1))
			else
				(= priority (CoordPri y))
				(= cel theCel)
			)
		)
		(super doit:)
	)
	
	(method (addToPic &tmp theY)
		(if (!= local0 3)
			(self posn: 289 128 0 loop: 4)
		else
			(self posn: 258 122 0 loop: 6)
		)
		(= cel (- (NumCels self) 1))
		(= theCel cel)
		(= priority (CoordPri y))
		(SetNowSeen self)
		(= theNsLeft nsLeft)
		(= theY y)
		(= y (- nsBottom 2))
		(= z (- y theY))
	)
)

(class Stair of View
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 10
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
		yStep 2
		view 801
		loop 1
		cel 0
		priority 0
		underBits 0
		signal $0101
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
	)
	
	(method (init)
		(super init: &rest)
		(walkHandler add: self)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(!= local0 2)
				(!= (event type?) evVERB)
				(not (event modifiers?))
				(& (event type?) evMOVE)
				(self onMe: event)
			)
			(event claimed: 1)
			(ego setMotion: PolyPath 188 151)
		)
		(event claimed?)
	)
	
	(method (onMe event)
		(if (super onMe: event)
			(if
				(and
					(== (theIconBar curIcon?) (theIconBar at: 2))
					(= noun 10)
				)
			else
				(= noun 11)
			)
		)
	)
)

(instance downStairs of Stair
	(properties
		x 106
		y 158
		signal $5000
	)
)

(instance downUpperHalf of Stair
	(properties
		x 106
		y 78
		cel 1
		signal $5000
	)
)

(instance downFloor of Stair
	(properties
		x 106
		y 77
		cel 2
		signal $5010
	)
)

(instance backPost of Stair
	(properties
		x 181
		y 158
		z 15
		cel 3
		signal $5000
	)
)

(instance upHallleft of View
	(properties
		x 25
		y 114
		noun 9
		view 801
		loop 2
		priority 15
		signal $5010
	)
)

(instance upHalleft2 of View
	(properties
		x 25
		y 114
		noun 9
		view 801
		loop 2
		cel 1
		signal $4010
	)
)

(instance upStairs of View
	(properties
		x 154
		y 148
		noun 11
		view 801
		loop 2
		cel 3
		signal $5010
	)
)

(instance upStairs2 of View
	(properties
		x 154
		y 151
		noun 11
		view 801
		loop 2
		cel 2
		signal $5000
	)
)

(instance walls of Feature
	(properties
		noun 7
		onMeCheck $0002
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(roomConv add: -1 noun theVerb 26)
				(if (>= ((User curEvent?) x?) 214)
					(roomConv add: -1 noun theVerb 27)
				)
				(roomConv init:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance roomConv of Conversation
	(properties)
)

(instance mainPoly of Polygon
	(properties)
)

(instance Poly1 of Polygon
	(properties)
)

(instance Poly2 of Polygon
	(properties)
)
