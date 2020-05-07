;;; Sierra Script 1.0 - (do not remove this comment)
(script# 21)
(include sci.sh)
(use Main)
(use NewRoomCue)
(use Kq6Procs)
(use Feature)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rCliffs 0
	proc21_1 1
)

(local
	[local0 200]
	[newRockStep 18]
	[newLetter 28]
	[local246 16] = [74 103 132 161 190 219 248 219 190 161 132 103 132 161 190 219]
	[local262 16] = [184 172 160 148 136 124 112 100 88 76 64 52 40 28 16 4]
	[local278 9] = [103 132 161 190 161 132 103 74 45]
	[local287 37] = [100 88 76 64 52 40 28 16 4]
	local324
	local325
	local326
	local327
	local328
)
(procedure (proc21_1)
	(if (== ((ScriptID 21 0) stepSound?) 1)
		((ScriptID 21 0) stepSound: 4)
	else
		((ScriptID 21 0)
			stepSound: (- ((ScriptID 21 0) stepSound?) 1)
		)
	)
	(soundFx
		number:
			(switch ((ScriptID 21 0) stepSound?)
				(1 301)
				(2 302)
				(3 303)
				(4 304)
			)
		setLoop: 1
		play:
	)
)

(class rCliffs of Rgn
	(properties
		script 0
		number 0
		modNum -1
		noun 0
		timer 0
		keep 0
		initialized 0
		cliffFace 0
		puzzleIsUp 0
		stepDirection 0
		stepSound 1
		cheatCount 0
	)
	
	(method (init)
		(super init:)
	)
	
	(method (dispose)
		(theIconBar enable: 6)
		(super dispose:)
	)
	
	(method (cue)
		(= local325 0)
	)
	
	(method (newRoom n)
		(= keep (OneOf n 300 320))
		(= initialized 0)
		(super newRoom: n &rest)
	)
	
	(method (notify)
		(= local325 1)
		(= local326 (Random 500 1000))
	)
)

(class CliffRoom of KQ6Room
	(properties
		script 0
		number 0
		modNum -1
		noun 0
		timer 0
		keep 0
		initialized 0
		picture 0
		style $ffff
		horizon 80
		controls 0
		north 0
		east 0
		south 0
		west 0
		curPic 0
		picAngle 0
		vanishingX 160
		vanishingY 0
		obstacles 0
		inset 0
		walkOffEdge 0
		autoLoad 1
		maxRocks 0
		rockCount 0
		flipRocks 0
		rockList 0
	)
	
	(method (init)
		(if (not rockList) ((= rockList (Set new:)) add:))
		(super init: &rest)
	)
	
	(method (handleEvent event &tmp temp0 temp1 [temp2 10])
		(if
			(and
				(User controls?)
				(& (event type?) evJOYSTICK)
				(!= (event message?) JOY_NULL)
				(== (theIconBar curIcon?) (theIconBar at: 0))
				(IsObject rockList)
			)
			(if
				(not
					(= temp0 (rockList firstTrue: #onMe (User curEvent?)))
				)
				(= temp0 (rockList firstTrue: #onMe ego))
			)
			(= temp1 (rockList indexOf: temp0))
			(switch (event message?)
				(JOY_UP
					(if (!= (rockList size?) (- temp1 1))
						(= temp0 (rockList at: (+ temp1 1)))
					)
				)
				(JOY_DOWN
					(if (!= temp1 0) (= temp0 (rockList at: (- temp1 1))))
				)
				(else  0)
			)
			(if (IsObject temp0)
				(SetCursor (+ (temp0 x?) 8) (temp0 y?))
			)
			(event claimed: 1)
			(return)
		else
			(super handleEvent: event)
		)
	)
	
	(method (newRoom)
		(directionHandler delete: curRoom)
		(if rockList
			(rockList release: dispose:)
			(= rockList 0)
		)
		(super newRoom: &rest)
	)
	
	(method (constantRocks &tmp temp0)
		(rCliffs cheatCount: 0)
		(= temp0 0)
		(while (< temp0 7)
			((= [newRockStep temp0] (RockStep new:))
				x: (if argc (- 320 [local246 temp0]) else [local246 temp0])
				y: [local262 temp0]
				cel: 3
				rockPointer: temp0
				corner: (if (== temp0 6) 1 else 0)
				init:
				stopUpd:
			)
			(rockList add: [newRockStep temp0])
			(if (< temp0 6) ([newRockStep temp0] addToPic:))
			(++ temp0)
		)
	)
	
	(method (callForRocks)
		(if (== curRoomNum 300)
			(self rockCount: 0 maxRocks: 9)
		else
			(self rockCount: 7 maxRocks: 16)
		)
		(self makeARock:)
	)
	
	(method (makeARock)
		(if (== rockCount maxRocks)
			(if (== curRoomNum 300) (messager say: 8 5 18 2 0 21))
			((curRoom script?) cue:)
		else
			(ShakeScreen 1 (Random 0 2))
			(soundFx number: 300 setLoop: 1 play:)
			(if (> rockCount 0)
				([newRockStep (- rockCount 1)] stopUpd:)
			)
			((= [newRockStep rockCount] (RockStep new:))
				x:
					(cond 
						((== curRoomNum 300) [local278 rockCount])
						(flipRocks (- 320 [local246 rockCount]))
						(else [local246 rockCount])
					)
				y:
					(if (== curRoomNum 300)
						[local287 rockCount]
					else
						[local262 rockCount]
					)
				cel:
					(if
						(or
							(== prevRoomNum 130)
							(== prevRoomNum 340)
							(== prevRoomNum 370)
						)
						2
					else
						0
					)
				rockPointer: rockCount
				corner:
					(cond 
						((and (== rockCount 3) (== curRoomNum 300)) 1)
						(
							(and
								(or (== rockCount 6) (== rockCount 11))
								(== curRoomNum 320)
							)
							1
						)
						(else 0)
					)
				capStone:
					(if
						(or
							(and (== rockCount 14) (== curRoomNum 320))
							(and (== rockCount 7) (== curRoomNum 300))
						)
						1
					else
						0
					)
				init:
				setCycle: End RockStep
			)
			(rockList add: [newRockStep rockCount])
			(self rockCount: (+ (self rockCount?) 1))
		)
	)
	
	(method (allRocksOut param1 &tmp temp0 temp1 temp2)
		(rCliffs cheatCount: 0)
		(if (== curRoomNum 300)
			(= temp1 9)
			(= temp2 0)
		else
			(= temp1 16)
			(= temp2 0)
		)
		(= temp0 temp2)
		(while (< temp0 temp1)
			((= [newRockStep temp0] (RockStep new:))
				x:
					(cond 
						((== curRoomNum 300) [local278 temp0])
						(param1 (- 320 [local246 temp0]))
						(else [local246 temp0])
					)
				y: (if (== curRoomNum 300)
					[local287 temp0]
				else
					[local262 temp0]
				)
				cel: 3
				rockPointer: temp0
				corner:
					(cond 
						((== curRoomNum 300) (if (== temp0 3) 1 else 0))
						((or (== temp0 6) (== temp0 11)) 1)
						(else 0)
					)
				capStone:
					(cond 
						((== curRoomNum 300) (if (== temp0 7) 1 else 0))
						((== temp0 14) 1)
						(else 0)
					)
				init:
				stopUpd:
			)
			(rockList add: [newRockStep temp0])
			(if (< temp0 6) ([newRockStep temp0] addToPic:))
			(++ temp0)
		)
		(UnLoad 128 300)
	)
	
	(method (dumpRocks &tmp temp0)
		(if rockList (rockList release:))
		(= temp0 0)
		(while (< temp0 16)
			(if (cast contains: [newRockStep temp0])
				([newRockStep temp0] dispose: delete:)
				(= [newRockStep temp0] 0)
			)
			(++ temp0)
		)
		(= temp0 0)
		(while (< temp0 16)
			(if (addToPics contains: [newRockStep temp0])
				([newRockStep temp0] dispose: delete:)
				(= [newRockStep temp0] 0)
			)
			(++ temp0)
		)
	)
)

(class RockStep of Prop
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
		yStep 2
		view 300
		loop 5
		cel 0
		priority 0
		underBits 0
		signal $4011
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
		cycleSpeed 24
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		corner 0
		rockPointer 0
		capStone 0
	)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
	)
	
	(method (doit)
		(if local325
			(cond 
				((ego script?))
				((> local326 0) (-- local326))
				(else (ego setScript: egoWobbles))
			)
		)
		(super doit:)
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
				(== (theIconBar curIcon?) (theIconBar at: 0))
			)
			(if
				(and
					(or
						(== (event message?) KEY_RETURN)
						(== (event type?) evMOUSEBUTTON)
					)
					(self onMe: event)
				)
				(event claimed: 1)
				(= local325 0)
				(cond 
					((== (ego script?) egoWobbles) (Bset 59) (messager say: 6 3 17 1 0 21))
					(
						(and
							(or
								(== (ego view?) 900)
								(== (ego view?) 308)
								(== (ego view?) 3081)
							)
							(== (self rockPointer?) 0)
						)
						(ego setScript: takeFirstStep 0 [newRockStep 0])
					)
					(
						(and
							(< (- (self y?) (ego y?)) 15)
							(> (- (self y?) (ego y?)) 2)
						)
						(ego
							setScript: stepDown 0 [newRockStep (self rockPointer?)]
						)
					)
					(
						(or
							(> (Abs (- (ego y?) (self y?))) 20)
							(> (Abs (- (ego x?) (self x?))) 64)
						)
						(messager say: 6 3 16 1 0 21)
					)
					(
						(or
							(and
								(< (- (self x?) (ego x?)) 40)
								(> (- (self x?) (ego x?)) 35)
								(< (self y?) (ego y?))
							)
							(and
								(< (- (ego x?) (self x?)) 57)
								(> (- (ego x?) (self x?)) 54)
								(< (self y?) (ego y?))
							)
						)
						(ego
							setScript: takeStep 0 [newRockStep (self rockPointer?)]
						)
					)
					(
						(and
							(> (self x?) (ego x?))
							(< (ego y?) 99)
							(== curRoomNum 300)
							(< (self y?) (ego y?))
						)
						(ego
							setScript: takeStep 0 [newRockStep (self rockPointer?)]
						)
					)
					(
					(and (> (self x?) (ego x?)) (< (self y?) (ego y?)))
						(ego
							setScript: takeStep 0 [newRockStep (self rockPointer?)]
						)
					)
					(
					(and (< (self x?) (ego x?)) (< (self y?) (ego y?)))
						(ego
							setScript: takeStep 0 [newRockStep (self rockPointer?)]
						)
					)
				)
			else
				(super handleEvent: event)
			)
		else
			(super handleEvent: event)
		)
		(event claimed?)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 6 1 0 1 0 21))
			(5 (messager say: 6 5 0 1 0 21))
			(2 (messager say: 6 2 0 1 0 21))
			(else 
				(messager say: 6 0 0 1 0 21)
			)
		)
	)
	
	(method (cue)
		(curRoom makeARock:)
	)
)

(class HyroGliph of View
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
		yStep 2
		view -1
		loop 0
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
		hyroVal 0
		deathButt 0
	)
)

(instance letter of HyroGliph
	(properties)
)

(class PuzzleInset of View
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
		yStep 2
		view -1
		loop 0
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
		maxButtons 0
		buttNumber 0
		buttView 0
		buttTop 0
		buttLeft 0
		buttRight 0
		buttBottom 0
		buttLoop 0
		buttCel 0
		buttX 0
		buttY 0
		buttVal 0
		buttKill 0
		buttonCount 0
		correctButton 1
		savePMouse 0
		solvedPuzz 0
		lookMsg 0
		puzzNumber 0
	)
	
	(method (init &tmp temp0)
		(theIconBar disable: 3 0 4 5 6)
		(= temp0 0)
		(while (< temp0 28)
			(= [newLetter temp0] 0)
			(++ temp0)
		)
		(rCliffs puzzleIsUp: 1)
		(self setPri: 12 signal: 4112 ignoreActors: stopUpd:)
		(super init: &rest)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
	)
	
	(method (dispose)
		(rCliffs puzzleIsUp: 0)
		(self dumpButtons:)
		(= correctButton (+ (= local324 (= buttonCount 0)) 1))
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(if (not local328) (theGame handsOn:))
		(theIconBar enable: 6)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(User canInput:)
				(!= (event type?) evVERB)
				(or
					(and
						(== (event message?) KEY_RETURN)
						(& (event type?) evKEYBOARD)
					)
					(== (event type?) evMOUSEBUTTON)
				)
				(not (event modifiers?))
			)
			(cond 
				(solvedPuzz (event claimed: 1) (self solvedPuzz: 0) (self dispose:))
				(
					(and
						(self onMe: event)
						(== (theIconBar curIcon?) (theIconBar at: 2))
					)
					(event claimed: 1)
					(messager say: lookMsg 1 0 1 0 21)
				)
				(
					(and
						(or
							(& (event type?) evMOUSEBUTTON)
							(and
								(& (event type?) evKEYBOARD)
								(== (event message?) KEY_RETURN)
							)
						)
						(self doButton: (event x?) (event y?))
					)
					(event claimed: 1)
				)
				((& (event type?) evMOUSEBUTTON)
					(event claimed: 1)
					(curRoom setScript: clearTheButtons 0 self)
				)
				(
					(and
						(& (event type?) evMOUSEBUTTON)
						(event modifiers?)
					)
					(event claimed: 0)
				)
				((& (event type?) evKEYBOARD)
					(cond 
						((== (event message?) KEY_RETURN)
							(event claimed: 1)
							(messager say: 8 5 7 1 0 21)
							(self dispose:)
						)
						(27
							(event claimed: 1)
							(messager say: 8 5 7 1 0 21)
							(self dispose:)
						)
						(else (event claimed: 0))
					)
				)
				(else (event claimed: 0))
			)
		else
			(event claimed: 0)
		)
		(event claimed?)
	)
	
	(method (doButton param1 param2 &tmp temp0 temp1)
		(= temp0 0)
		(return
			(while (< temp0 buttNumber)
				(if
					(and
						(<= (WordAt buttLeft temp0) param1)
						(<= param1 (WordAt buttRight temp0))
						(<= (WordAt buttTop temp0) param2)
						(<= param2 (WordAt buttBottom temp0))
					)
					(if (== [newLetter temp0] 0)
						(if (and (== curRoomNum 300) (== local324 3))
							(= global110 temp0)
						)
						(if (or (== puzzNumber 2) (== puzzNumber 4))
							(= temp1 2)
						else
							(= temp1 0)
						)
						(++ local324)
						(soundFx number: 308 setLoop: 1 play:)
						((= [newLetter temp0] (letter new:))
							view: buttView
							setLoop: (WordAt buttLoop temp0)
							cel: (WordAt buttCel temp0)
							x: (+ (WordAt buttX temp0) temp1)
							y: (WordAt buttY temp0)
							hyroVal: (WordAt buttVal temp0)
							deathButt: (WordAt buttKill temp0)
							setPri: 14
							init:
							stopUpd:
						)
						(= local327 1)
						(if ([newLetter temp0] deathButt?)
							(= local328 1)
							(rCliffs cue:)
							(curRoom setScript: seeYa 0 self)
						else
							(self cue: ([newLetter temp0] hyroVal?))
						)
						(return 1)
						(break)
					)
					(return 1)
					(break)
				)
				(++ temp0)
			)
		)
	)
	
	(method (cue param1)
		(self buttonCount: (+ (self buttonCount?) 1))
		(cond 
			((or (== param1 correctButton) (== param1 -1))
				(self correctButton: (+ (self correctButton?) 1))
				(if (== correctButton (+ maxButtons 1))
					(= correctButton (+ (= buttonCount 0) 1))
					(curRoom setScript: notifyTheRoom 0 self)
				)
			)
			((== buttonCount buttNumber) (curRoom setScript: clearTheButtons 0 self))
			((!= param1 correctButton) (self correctButton: (+ (self correctButton?) 10)))
		)
	)
	
	(method (dumpButtons &tmp temp0)
		(= temp0 0)
		(while (< temp0 28)
			(if (!= [newLetter temp0] 0)
				([newLetter temp0] dispose:)
				(= [newLetter temp0] 0)
			)
			(++ temp0)
		)
	)
	
	(method (puzzSolved &tmp [temp0 2])
		(curRoom setScript: puzzleSolvedPause 0 self)
	)
)

(instance stepDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(rCliffs cheatCount: (- (rCliffs cheatCount?) 1))
				(cond 
					(
					([newRockStep (+ (register rockPointer?) 1)] corner?)
						(if (< (ego loop?) 6)
							(rCliffs stepDirection: 1)
						else
							(rCliffs stepDirection: 2)
						)
						(= ticks 4)
					)
					((== (rCliffs stepDirection?) 4)
						(ego
							view: 3011
							setLoop: 1
							cel: 0
							x: (+ (ego x?) 18)
							y: (+ (ego y?) 11)
						)
						(rCliffs stepDirection: 1)
						(= cycles 6)
					)
					((== (rCliffs stepDirection?) 3)
						(ego
							view: 3011
							setLoop: 1
							cel: 0
							x: (- (ego x?) 18)
							y: (+ (ego y?) 11)
						)
						(rCliffs stepDirection: 2)
						(= cycles 6)
					)
					(else (self cue:))
				)
			)
			(1
				(if (== (rCliffs stepSound?) 1)
					(rCliffs stepSound: 4)
				else
					(rCliffs stepSound: (- (rCliffs stepSound?) 1))
				)
				(soundFx
					number:
						(switch (rCliffs stepSound?)
							(1 301)
							(2 302)
							(3 303)
							(4 304)
						)
					setLoop: 1
					play:
				)
				(if (== (rCliffs stepDirection?) 2)
					(ego
						view: 301
						setLoop: 7
						cel: 0
						cycleSpeed: 16
						posn: (+ (register x?) 24) (- (register y?) 13)
					)
				else
					(ego
						view: 301
						setLoop: 8
						cel: 0
						posn: (- (register x?) 6) (- (register y?) 13)
						cycleSpeed: 16
					)
				)
				(= cycles 6)
			)
			(2
				(ego cel: 1)
				(if (== (rCliffs stepDirection?) 2)
					(ego posn: (+ (register x?) 19) (- (register y?) 11))
				else
					(ego posn: (- (register x?) 3) (- (register y?) 11))
				)
				(= cycles 5)
			)
			(3
				(ego cel: 2)
				(if (== (rCliffs stepDirection?) 2)
					(ego posn: (+ (register x?) 23) (- (register y?) 8))
				else
					(ego posn: (- (register x?) 5) (- (register y?) 11))
				)
				(= cycles 5)
			)
			(4
				(ego cel: 3)
				(if (== (rCliffs stepDirection?) 2)
					(ego posn: (+ (register x?) 18) (- (register y?) 14))
				else
					(ego posn: (- (register x?) 5) (- (register y?) 14))
				)
				(= cycles 5)
			)
			(5
				(ego cel: 4)
				(if (== (rCliffs stepDirection?) 2)
					(ego posn: (+ (register x?) 19) (- (register y?) 14))
				else
					(ego posn: (- (register x?) 6) (- (register y?) 14))
				)
				(= cycles 5)
			)
			(6
				(ego cel: 5)
				(if (== (rCliffs stepDirection?) 2)
					(ego posn: (+ (register x?) 19) (- (register y?) 14))
				else
					(ego posn: (- (register x?) 6) (- (register y?) 14))
				)
				(= cycles 5)
			)
			(7
				(ego cel: 6)
				(if (== (rCliffs stepDirection?) 2)
					(ego posn: (+ (register x?) 19) (- (register y?) 14))
				else
					(ego posn: (- (register x?) 4) (- (register y?) 14))
				)
				(= cycles 5)
			)
			(8
				(cond 
					(
						(and
							(== (register corner?) 1)
							(== (rCliffs stepDirection?) 2)
						)
						(ego
							setLoop: 5
							cel: 0
							cycleSpeed: 7
							posn: (+ (register x?) 9) (+ (ego y?) 18)
							setCycle: End self
						)
						(rCliffs stepDirection: 3)
					)
					((== (register corner?) 1)
						(ego
							setLoop: 4
							cel: 0
							cycleSpeed: 7
							posn: (+ (register x?) 8) (+ (ego y?) 18)
							setCycle: End self
						)
						(rCliffs stepDirection: 4)
					)
					(else (self cue:))
				)
			)
			(9
				(cond 
					((and (register corner?) (== (ego loop?) 5))
						(ego
							posn: (+ (register x?) 27) (- (register y?) 2)
							setLoop: (Random 1 2)
							cel: 0
						)
					)
					((and (register corner?) (== (ego loop?) 4))
						(ego
							posn: (- (register x?) 9) (- (register y?) 1)
							setLoop: 6
							cel: 0
						)
					)
					((== (rCliffs stepDirection?) 1)
						(ego
							view: 301
							setLoop: (Random 1 2)
							cel: 0
							posn: (+ (register x?) 26) (- (register y?) 1)
						)
					)
					(else
						(ego
							view: 301
							setLoop: 6
							cel: 0
							posn: (- (register x?) 8) (- (register y?) 1)
						)
					)
				)
				(= cycles 6)
			)
			(10
				(theGame handsOn:)
				(if (!= (register corner?) 1)
					(= local325 1)
					(= local326 (Random 1000 2000))
				)
				(if (== (register rockPointer?) 0)
					(if (== ((ScriptID 21 0) cliffFace?) 0)
						(curRoom cue: -1)
					else
						(curRoom cue: 0)
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance takeFirstStep of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego actions: egoStepVerb setMotion: MoveTo 110 112 self)
			)
			(1
				(rCliffs stepSound: 1)
				(soundFx number: 301 setLoop: 1 play:)
				(ego view: 301 normal: 0 setLoop: 0 cel: 0)
				(= ticks 6)
			)
			(2 (ego cel: 1) (= ticks 6))
			(3
				(ego
					cel: 2
					posn: (+ (register x?) 7) (+ (register y?) 9)
					cycleSpeed: 10
					setCycle: End self
				)
			)
			(4
				(theGame handsOn:)
				(ego
					posn: (+ (register x?) 26) (- (register y?) 2)
					setLoop: 2
					cel: 0
				)
				(= local325 1)
				(rCliffs stepDirection: 3)
				(= local326 (Random 1000 2000))
				(directionHandler add: curRoom)
				(self dispose:)
			)
		)
	)
)

(instance takeStep of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(rCliffs cheatCount: (+ (rCliffs cheatCount?) 1))
				(cond 
					((== (rCliffs stepDirection?) 2)
						(ego
							view: 3011
							setLoop: 1
							cel: 0
							posn: (- (register x?) 19) (+ (register y?) 21)
						)
						(rCliffs stepDirection: 3)
						(= cycles 2)
					)
					((== (rCliffs stepDirection?) 1)
						(ego
							view: 3011
							setLoop: 1
							cel: 0
							posn: (+ (register x?) 37) (+ (register y?) 19)
						)
						(rCliffs stepDirection: 4)
						(= cycles 2)
					)
					(else (self cue:))
				)
			)
			(1
				(if (== (rCliffs stepSound?) 4)
					(rCliffs stepSound: 1)
				else
					(rCliffs stepSound: (+ (rCliffs stepSound?) 1))
				)
				(soundFx
					number:
						(switch (rCliffs stepSound?)
							(1 301)
							(2 302)
							(3 303)
							(4 304)
						)
					setLoop: 1
					play:
				)
				(if (== (rCliffs stepDirection?) 3)
					(ego
						view: 301
						cycleSpeed: 10
						setLoop: (Random 1 2)
						cel: 0
						posn: (- (register x?) 2) (+ (register y?) 10)
						setCycle: End self
					)
				else
					(ego
						view: 301
						setLoop: 6
						cycleSpeed: 10
						posn: (+ (register x?) 20) (+ (register y?) 11)
						setCycle: End self
					)
				)
			)
			(2
				(if (== (rCliffs stepDirection?) 3)
					(ego
						posn: (+ (register x?) 27) (- (register y?) 2)
						cel: 0
					)
				else
					(ego
						posn: (- (register x?) 9) (- (register y?) 1)
						cel: 0
					)
				)
				(= ticks 6)
			)
			(3
				(cond 
					(
						(and
							(== (register corner?) 1)
							(== (rCliffs stepDirection?) 3)
						)
						(ego
							setLoop: 4
							cycleSpeed: 12
							posn: (+ (register x?) 7) (+ (ego y?) 7)
							setCycle: End self
						)
					)
					((== (register corner?) 1)
						(ego
							setLoop: 5
							cycleSpeed: 12
							posn: (+ (register x?) 10) (+ (ego y?) 5)
							setCycle: End self
						)
					)
					(else (self cue:))
				)
			)
			(4
				(cond 
					(
						(and
							(register corner?)
							(== (rCliffs stepDirection?) 3)
						)
						(ego
							posn: (- (register x?) 9) (- (register y?) 1)
							setLoop: 6
							cel: 0
						)
						(rCliffs stepDirection: 4)
					)
					((register corner?)
						(ego
							posn: (+ (register x?) 27) (- (register y?) 2)
							setLoop: (Random 1 2)
							cel: 0
						)
						(rCliffs stepDirection: 3)
					)
				)
				(= ticks 6)
			)
			(5
				(theGame handsOn:)
				(if (!= (register corner?) 1)
					(= local325 1)
					(= local326 (Random 1000 2000))
				)
				(if (== (register capStone?) 1)
					(if (== (rCliffs stepDirection?) 4)
						(rCliffs stepDirection: 3)
					else
						(rCliffs stepDirection: 4)
					)
					(ego setScript: nextScreenUp 0 register)
				)
				(self dispose:)
			)
		)
	)
)

(instance nextScreenUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== (rCliffs stepSound?) 4)
					(rCliffs stepSound: 1)
				else
					(rCliffs stepSound: (+ (rCliffs stepSound?) 1))
				)
				(soundFx
					number:
						(switch (rCliffs stepSound?)
							(1 301)
							(2 302)
							(3 303)
							(4 304)
						)
					setLoop: 1
					play:
				)
				(if (== (rCliffs stepDirection?) 4)
					(ego
						cycleSpeed: 10
						setLoop: (Random 1 2)
						setCycle: End self
					)
				else
					(ego setLoop: 6 cycleSpeed: 10 setCycle: End self)
				)
			)
			(1
				(if (== (rCliffs stepDirection?) 4)
					(ego
						posn: (+ (register x?) 56) (- (register y?) 14)
						cel: 0
					)
				else
					(ego
						posn: (- (register x?) 38) (- (register y?) 13)
						cel: 0
					)
				)
				(= ticks 6)
			)
			(2
				(theGame handsOn:)
				(ego hide:)
				(if (== curRoomNum 320)
					(curRoom cue: 1)
					(self dispose:)
				else
					(curRoom newRoom: (curRoom north?))
				)
			)
		)
	)
)

(instance notifyTheRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame givePoints: 1)
				(register dispose:)
				(= cycles 1)
			)
			(1
				(switch (rCliffs cliffFace?)
					(0
						(switch curRoomNum
							(300 (Bset 5))
							(320 (Bset 123))
						)
					)
					(1 (Bset 124))
					(2 (Bset 125))
					(3 (Bset 126) (Bset 6))
				)
				(if (== curRoomNum 320)
					(messager say: 4 5 6 1 self 21)
				else
					(messager say: 8 5 18 1 self 21)
				)
			)
			(2
				(curRoom notify:)
				(self dispose:)
			)
		)
	)
)

(instance egoWobbles of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(soundFx number: 305 setLoop: 1 play:)
				(if (== (ego loop?) 6)
					(ego
						view: 3011
						posn: (+ (ego x?) 18) (+ (ego y?) 10)
						cycleSpeed: 12
						setLoop: 1
					)
				else
					(ego
						view: 301
						posn: (- (ego x?) 18) (+ (ego y?) 7)
						cycleSpeed: (Random 4 16)
						setLoop: 3
					)
				)
				(ego cel: 0 setCycle: End self)
			)
			(1
				(if (== (ego view?) 301)
					(ego setCycle: Beg self)
				else
					(self cue:)
				)
			)
			(2
				(if (== (ego view?) 301)
					(ego
						posn: (+ (ego x?) 18) (- (ego y?) 7)
						setLoop: (Random 1 2)
					)
				else
					(ego posn: (- (ego x?) 18) (- (ego y?) 10) setLoop: 6)
				)
				(ego view: 301 cycleSpeed: 18 cel: 0)
				(= local326 (Random 1000 2000))
				(Bclr 59)
				(self dispose:)
			)
		)
	)
)

(instance seeYa of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theIconBar disable: 6)
				(register dispose:)
				(= ticks 4)
			)
			(1
				(messager say: 3 5 3 1 self 21)
			)
			(2
				([newRockStep 6] setCycle: Beg self)
				(soundFx number: 300 setLoop: 1 play:)
			)
			(3
				(messager say: 3 5 3 2 self 21)
			)
			(4
				(ego
					posn: (- (ego x?) 10) (ego y?)
					ignoreActors:
					illegalBits: 0
					view: 4011
					normal: 0
					cycleSpeed: 6
					setLoop: 0
					setCycle: CT 10 1 self
				)
			)
			(5
				(soundFx number: 306 setLoop: 1 play: self)
				(ego setCycle: End)
			)
			(6 (ego y: 280) (= seconds 2))
			(7
				(soundFx number: 307 setLoop: 1 play:)
				(ShakeScreen 2 2)
				(= ticks 4)
			)
			(8
				(messager say: 3 5 3 3 self 21)
			)
			(9
				(soundFx2 fade: 0 5 5)
				(EgoDead 6)
			)
		)
	)
)

(instance clearTheButtons of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(register dispose:)
				(= cycles 4)
			)
			(2
				(if local327
					(messager say: 8 5 7 1 self 21)
				else
					(self cue:)
				)
			)
			(3
				(= local327 0)
				(self dispose:)
			)
		)
	)
)

(instance puzzleSolvedPause of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(messager say: 8 5 8 1 self 21)
			)
			(2
				(if (== curRoomNum 320)
					(if (== (ego cel?) 1)
						(ego view: 301 setLoop: 6 cel: 0)
					else
						(ego view: 301 setLoop: 1)
					)
				)
				(UnLoad 128 3012)
				(self dispose:)
			)
		)
	)
)

(instance egoStepVerb of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1 (return 0))
				(5 (return 0))
				(2 (return 0))
				(else 
					(messager say: 0 0 64 1 0 899)
					(return 1)
				)
			)
		)
	)
)
