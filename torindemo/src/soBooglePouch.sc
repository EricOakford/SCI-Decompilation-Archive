;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64018)
(include sci.sh)
(use Main)
(use TPOrbit)
(use TPWander)
(use TPChase)
(use List)
(use Script)
(use CueMe)
(use Array)
(use Print)
(use Scaler)
(use PolyPath)
(use Feature)
(use StopWalk)
(use Grooper)
(use Motion)
(use Actor)

(public
	oBoogle 0
	soBoogleIntoPouch 1
	soBoogleOutOfPouch 2
	proc64018_3 3
	proc64018_4 4
	oBoogleStartWandering 5
)

(local
	local0
	local1
	local2
	local3
)
(procedure (proc64018_3 param1 param2 param3 theCurRoomObstacles &tmp temp0 newIntArray temp2 curRoomObstacles temp4 temp5)
	(= temp2 (param1 plane?))
	(if (== 4 argc)
		(= curRoomObstacles theCurRoomObstacles)
	else
		(= curRoomObstacles (curRoom obstacles?))
	)
	(= temp5
		(AvoidPath
			(param1 x?)
			(param1 y?)
			param2
			param3
			curRoomObstacles
			(+ (- (temp2 right:) (temp2 left:)) 1)
			(+ (- (temp2 bottom?) (temp2 top?)) 1)
			1
		)
	)
	((= newIntArray (IntArray new:)) copy: temp5)
	(KArray 4 temp5)
	(= temp4 2)
	(while (!= (newIntArray at: temp4) 30583)
		(= temp4 (+ temp4 2))
	)
	(= temp0
		(IntArray
			newWith: 2 (newIntArray at: (- temp4 2)) (newIntArray at: (- temp4 1))
		)
	)
	(newIntArray dispose:)
	(return temp0)
)

(procedure (proc64018_4 param1 param2 param3 &tmp temp0 temp1)
	(asm
		_line_   138
		_file_   {filename}
		_line_   145
		_line_   147
		pushi    3
		lsp      param1
		lsp      param2
		lsp      param3
		call     proc64018_3,  6
		sat      temp0
		_line_   148
		_line_   149
		lsp      param2
		pushi    #at
		pushi    1
		pushi    0
		send     6
		ne?     
		bt       code_0358
		_line_   150
		lsp      param3
		pushi    #at
		pushi    1
		pushi    1
		lat      temp0
		send     6
		ne?     
code_0358:
		sat      temp1
		_line_   153
		pushi    #dispose
		pushi    0
		lat      temp0
		send     4
		_line_   155
		lat      temp1
		ret     
	)
)

(procedure (localproc_0370 param1 &tmp temp0 temp1 temp2)
	(asm
		_line_   162
		_file_   {filename}
		_line_   168
		_line_   174
		pushi    #scaleX
		pushi    0
		lag      ego
		send     4
		sat      temp1
		_line_   179
		pushi    #x
		pushi    0
		lag      ego
		send     4
		sal      local0
		_line_   180
		pushi    #y
		pushi    0
		lag      ego
		send     4
		sal      local1
		_line_   183
		lsl      local0
		pushi    3
		pushi    54
		lst      temp1
		pushi    128
		callk    MulDiv,  6
		add     
		sal      local2
		_line_   184
		lsl      local1
		ldi      1
		add     
		sal      local3
		_line_   185
		ldi      1
		sat      temp0
		_line_   188
		lap      param1
		bnt      code_04b2
		pushi    3
		lsg      ego
		lsl      local2
		lsl      local3
		call     proc64018_4,  6
		bnt      code_04b2
		_line_   192
		lsl      local0
		pushi    3
		pushi    65472
		lst      temp1
		pushi    128
		callk    MulDiv,  6
		add     
		sal      local2
		_line_   193
		lsl      local1
		ldi      1
		add     
		sal      local3
		_line_   194
		ldi      0
		sat      temp0
		_line_   196
code_040f:
		pushi    3
		lsg      ego
		lsl      local2
		lsl      local3
		call     proc64018_4,  6
		bnt      code_04b2
		_line_   200
		lsl      local1
		ldi      190
		gt?     
		bnt      code_043d
		_line_   201
		lsl      local1
		pushi    2
		pushi    10
		pushi    30
		callk    Random,  4
		sub     
		sal      local1
		jmp      code_0451
code_043d:
		_line_   202
		_line_   203
		lsl      local1
		pushi    2
		pushi    10
		pushi    30
		callk    Random,  4
		add     
		sal      local1
code_0451:
		_line_   205
		lsl      local0
		pushi    3
		pushi    54
		lst      temp1
		pushi    128
		callk    MulDiv,  6
		add     
		sal      local2
		_line_   206
		lsl      local1
		ldi      1
		add     
		sal      local3
		_line_   207
		ldi      1
		sat      temp0
		_line_   210
		pushi    3
		lsg      ego
		lsl      local2
		lsl      local3
		call     proc64018_4,  6
		bnt      code_040f
		_line_   211
		lsl      local0
		pushi    3
		pushi    65472
		lst      temp1
		pushi    128
		callk    MulDiv,  6
		add     
		sal      local2
		_line_   212
		lsl      local1
		ldi      1
		add     
		sal      local3
		_line_   213
		ldi      0
		sat      temp0
		jmp      code_040f
code_04b2:
		_line_   217
		lat      temp0
		ret     
	)
)

(instance poBooglePouch of Prop
	(properties
		view -5334
	)
	
	(method (init)
		(if (ego scaler?)
			(self
				nCurPosX: (ego scaleX?) (ego scaleY?)
				setScaler:
					Scaler
					((ego scaler?) frontSize?)
					((ego scaler?) backSize?)
					((ego scaler?) frontY?)
					((ego scaler?) backY?)
			)
		)
		(super init: &rest)
	)
)

(class soBooglePouch of Script
	(properties
		scratch 0
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
		m_sm 0
		sb 0
		setNoScore 0
		unSet 0
		flag 0
	)
	
	(method (init)
		(= sb (localproc_0370 m_sm))
		(= setNoScore (ego x?))
		(= unSet (ego y?))
		(= flag (ego heading?))
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance soMoveTorin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath local0 local1 self)
			)
			(1 (ego setHeading: 180 self))
			(2 (self dispose:))
		)
	)
)

(instance soBoogleIntoPouch of soBooglePouch
	(properties)
	
	(method (init)
		(= m_sm 1)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(self setScript: LOOKUP_ERROR self)
				(LOOKUP_ERROR
					approachX: local2
					approachY: local3
					toggle: self
					init:
				)
				(if (not ((curRoom checkVerbHandlers?) size:))
					(LOOKUP_ERROR oFace: 0)
				)
				(LOOKUP_ERROR bSwing: 1)
			)
			(1)
			(2
				(if sb
					((ScriptID 64018 0) setHeading: 270 self)
					(LOOKUP_ERROR loop: 3)
				else
					((ScriptID 64018 0) setHeading: 90 self)
					(LOOKUP_ERROR loop: 2)
				)
			)
			(3
				((ScriptID 64018 0) hide:)
				(LOOKUP_ERROR
					setCel: 0
					posn: (ego x?) (ego y?)
					init:
					setCycle: End self
				)
			)
			(4
				(LOOKUP_ERROR dispose:)
				(ego setMotion: PolyPath setNoScore unSet self)
			)
			(5 (ego setHeading: flag self))
			(6
				((ScriptID 64017 0) set: 144)
				(self dispose:)
			)
		)
	)
)

(instance soBoogleOutOfPouch of soBooglePouch
	(properties)
	
	(method (init)
		(= m_sm 0)
		(super init: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(self setScript: LOOKUP_ERROR self)
			)
			(1
				(LOOKUP_ERROR
					loop: (if sb 0 else 1)
					setCel: 0
					posn: (ego x?) (ego y?)
					init:
					setCycle: End self
				)
			)
			(2
				(LOOKUP_ERROR dispose:)
				((ScriptID 64017 0) clear: 144)
				((ScriptID 64018 0)
					oPanner:
					loop: 8
					cel: (if sb 4 else 5)
					posn: local2 local3
				)
				(if (ego scaler?)
					((ScriptID 64018 0)
						nCurPosX: (ego scaleX?) (ego scaleY?)
						setScaler:
							Scaler
							((ego scaler?) frontSize?)
							((ego scaler?) backSize?)
							((ego scaler?) frontY?)
							((ego scaler?) backY?)
					)
				)
				((ScriptID 64018 0) init:)
				(ego setMotion: PolyPath setNoScore unSet self)
			)
			(3 (ego setHeading: flag self))
			(4 (self dispose:))
		)
	)
)

(class BoogleLearnMeClass of Feature
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		toggle 0
	)
	
	(method (init theToggle)
		(if argc (= toggle theToggle))
		(super init: &rest)
	)
	
	(method (tile)
		(LOOKUP_ERROR nSaveTime: self self)
	)
	
	(method (cue)
		(LOOKUP_ERROR setScript: toggle LOOKUP_ERROR)
	)
)

(class oBoogleStartWandering of CueMe
	(properties
		scratch 0
	)
	
	(method (cue)
		((ScriptID 64018 0) bSwing: 1)
	)
)

(instance poBoogleTailFidget of Prop
	(properties
		view -5331
		loop -1
	)
	
	(method (init)
		(if (== loop 8)
			(= loop ((ScriptID 64018 0) cel:))
		else
			(= loop ((ScriptID 64018 0) loop?))
		)
		(= cel 0)
		(= x ((ScriptID 64018 0) x?))
		(= y ((ScriptID 64018 0) y?))
		(super init: ((ScriptID 64018 0) plane?))
		(if ((ScriptID 64018 0) scaler?)
			(self
				nCurPosX: ((ScriptID 64018 0) scaleX?) ((ScriptID 64018 0) scaleY?)
				setScaler:
					Scaler
					(((ScriptID 64018 0) scaler?) frontSize?)
					(((ScriptID 64018 0) scaler?) backSize?)
					(((ScriptID 64018 0) scaler?) frontY?)
					(((ScriptID 64018 0) scaler?) backY?)
			)
		)
		(self setCycle: Fwd)
		((ScriptID 64018 0) hide:)
	)
	
	(method (dispose)
		((ScriptID 64018 0) show:)
		(super dispose:)
		(= loop -1)
	)
)

(instance oBoogleChase of TPChase
	(properties)
	
	(method (init)
		(= obstacles ((ScriptID 64018 0) obstacles?))
		(super init: &rest)
	)
)

(instance oBoogleOrbit of TPOrbit
	(properties)
	
	(method (init)
		(= obstacles ((ScriptID 64018 0) obstacles?))
		(super init: &rest)
	)
)

(instance oBoogleWander of TPWander
	(properties)
	
	(method (init)
		(= obstacles ((ScriptID 64018 0) obstacles?))
		(super init: &rest)
	)
)

(class BoogleActor of Actor
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $0021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		origStep 770
		moveSpeed 6
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
		robot 0
		nYTarget 0
		oFace 0
		pos 1
		obstacles 0
	)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and (self isNotHidden:) pos (< (-- oFace) 0))
			(self rotate:)
		)
	)
	
	(method (dispose)
		(self ID:)
		(super dispose:)
	)
	
	(method (setTile param1 param2 param3 &tmp temp0)
		(self setMotion: 0)
		(= temp0 (proc64018_3 self param1 param2))
		(self posn: (temp0 at: 0) (temp0 at: 1))
		(temp0 dispose:)
		(if (and (>= argc 3) param3) (param3 cue:))
	)
	
	(method (rotate &tmp temp0 temp1 curRoomCheckVerbHandlers temp3 temp4 temp5 [temp6 3])
		(self setMotion: 0)
		(if (!= -1 (LOOKUP_ERROR loop?))
			(LOOKUP_ERROR dispose:)
		)
		(= oFace (Random 50 250))
		(= temp1 (Random 1 100))
		(cond 
			(
				(and
					(= curRoomCheckVerbHandlers (curRoom checkVerbHandlers?))
					(> (curRoomCheckVerbHandlers size:) 0)
					(= temp3 (curRoomCheckVerbHandlers at: 0))
				)
				(self bSwing: 0)
				(temp3 tile:)
			)
			((> (= temp4 (self distanceTo: ego)) 500)
				(= temp5 (GetAngle (ego x?) (ego y?) x y))
				(= temp4 40)
				(self
					setTile:
						(+ (ego x?) (SinMult temp5 temp4))
						(+ (ego y?) (CosMult temp5 temp4))
						oBoogleStartWandering
				)
			)
			(
				(and
					(!= mover LOOKUP_ERROR)
					(> (= temp4 (self distanceTo: ego)) 200)
				)
				(self
					setMotion: LOOKUP_ERROR ego 40 oBoogleStartWandering
				)
			)
			((and (< temp1 10) (!= LOOKUP_ERROR mover)) (self setMotion: LOOKUP_ERROR 80))
			((and (< temp1 15) (!= LOOKUP_ERROR mover))
				(self
					setMotion:
						LOOKUP_ERROR
						ego
						40
						(GetAngle (ego x?) (ego y?) x y)
						(if (& $0001 temp1) -15 else 15)
				)
			)
			((< temp1 0) (LOOKUP_ERROR init:))
		)
	)
	
	(method (bSwing param1)
		(if param1 (= pos 1) (= oFace 0) else (= pos 0))
	)
	
	(method (addObstacle param1)
		(if (not obstacles) (= obstacles (List new:)))
		(obstacles add: param1 &rest)
	)
	
	(method (ID)
		(if obstacles (obstacles dispose:) (= obstacles 0))
	)
)

(instance oBoogle of BoogleActor
	(properties
		view -5336
	)
	
	(method (init)
		(if ((ScriptID 64017 0) test: 144)
			(Prints LOOKUP_ERROR)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (Prints LOOKUP_ERROR))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (oPanner)
		(self
			view: -5336
			z: 0
			ID:
			setStep: 6 4
			setSpeed: (theGame oCantBeHereHandler?)
			setScale: 0
			setScaler: 0
			setLooper: Grooper
			setLoop: -1
			setPri: -1
			setMotion: 0
			setCycle: StopWalk -1
			ignoreActors: 1
			bSwing: (not ((ScriptID 64017 0) test: 144))
		)
	)
)

(instance oHereBoy of BoogleLearnMeClass
	(properties)
	
	(method (init)
		(super init: &rest)
		(curRoom setDefault: self)
	)
	
	(method (tile)
		(curRoom newTarget: self)
		(LOOKUP_ERROR nSaveTime: self toggle)
	)
)
