;;; Sierra Script 1.0 - (do not remove this comment)
(script# 80)
(include sci.sh)
(use Main)
(use KQ6Room)
(use Kq6Procs)
(use Scaler)
(use PolyPath)
(use StopWalk)
(use Grooper)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rgCastle 0
	proc80_2 2
	guardsGetEgo 4
	guard1 5
	guard2 6
	proc80_7 7
)

(local
	local0
	local1
	local2
)
(procedure (proc80_2 param1 &tmp temp0 temp1)
	(theGame handsOff:)
	(switch param1
		(4 (= temp0 -40) (= temp1 0))
		(2 (= temp0 40) (= temp1 0))
		(1 (= temp0 0) (= temp1 -40))
	)
	(ego
		ignoreActors: 1
		setMotion: MoveTo (+ (ego x?) temp0) (+ (ego y?) temp1)
	)
)

(procedure (proc80_7 &tmp temp0 temp1)
	(= temp0 (localproc_0b22 (ScriptID 80 5)))
	(if
	(<= temp0 (= temp1 (localproc_0b22 (ScriptID 80 6))))
		(ScriptID 80 5)
	else
		(ScriptID 80 6)
	)
)

(procedure (localproc_0a07 param1 param2 param3 &tmp temp0 temp1 temp2 temp3 temp4)
	(= temp2 (- (ego brLeft?) param2))
	(= temp0 (- (ego brTop?) param3))
	(= temp3 (+ (ego brRight?) param2))
	(= temp1 (+ (ego brBottom?) param3))
	(if (not local2)
		(if (curRoom moveOtherGuard?) (++ local2))
		(= local0 (Max temp2 (Min temp3 (param1 x?))))
		(= local1 (Max temp0 (Min temp1 (param1 y?))))
	else
		(= local2 0)
		(= temp4 (- (* (<= (ego x?) (param1 x?)) 2) 1))
		(= local0
			(+
				(ego x?)
				(*
					(+ (/ (- (ego brRight?) (ego brLeft?)) 2) param2)
					temp4
				)
			)
		)
		(= local1 (Max temp0 (Min temp1 (param1 y?))))
	)
)

(procedure (localproc_0b22 param1 &tmp temp0)
	(if (cast contains: param1)
		(= temp0
			(GetDistance
				(param1 x?)
				(param1 y?)
				(ego x?)
				(ego y?)
				60
			)
		)
	else
		(= temp0 500)
	)
	(return temp0)
)

(class CastleRoom of KQ6Room
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
		spotEgoScr 0
		minScaleSize 10
		maxScaleSize 100
		minScaleY 0
		maxScaleY 190
		moveOtherGuard 0
		scalerCode 0
	)
	
	(method (dispose)
		(= scalerCode (= spotEgoScr 0))
		(super dispose:)
	)
	
	(method (newRoom)
		(if
			(and
				(!= (theGlobalSound prevSignal?) -1)
				(== (theGlobalSound number?) 710)
			)
			(theGlobalSound fade:)
		)
		(super newRoom: &rest)
	)
	
	(method (scriptCheck param1 &tmp temp0)
		(= temp0 0)
		(if
			(and
				(OneOf param1 85 87 93)
				(OneOf
					curRoomNum
					710
					720
					730
					770
					780
					781
					800
					810
					820
					840
					850
					860
					870
					880
				)
			)
			(switch param1
				(85
					(messager say: 0 0 199 0 0 899)
				)
				(87
					(messager say: 0 0 198 0 0 899)
				)
				(93
					(messager say: 0 0 3 0 0 899)
				)
			)
		else
			(= temp0 1)
		)
		(return temp0)
	)
	
	(method (doLoiter)
	)
	
	(method (warnUser)
	)
	
	(method (spotEgo param1)
		(theMusic stop:)
		(theGlobalSound number: 710 loop: -1 play:)
		(if spotEgoScr
			(curRoom setScript: spotEgoScr 0 param1)
		else
			(param1 setScript: guardsGetEgo &rest)
		)
	)
)

(class GuardDog of Actor
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
		sightAngle 95
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
		signal $0000
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
		cycleSpeed 10
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
		moveSpeed 10
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
		okToCheck 0
		checkCode 0
		regPathID 0
	)
	
	(method (init)
		(self setScale:)
		(super init: &rest)
		(self
			setCycle: StopWalk -1
			setLoop: GradualLooper
			setStep: 4 2
			ignoreHorizon:
			illegalBits: 0
			ignoreActors: 1
			signal: (| signal $1000)
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				okToCheck
				(or
					(not (IsObject okToCheck))
					(self perform: okToCheck)
				)
				(IsObject checkCode)
				(self perform: checkCode)
			)
			(= checkCode 0)
			(theGame handsOff:)
			(self setMotion: 0)
			(curRoom spotEgo: self)
		)
		(if (and (IsObject curRoom) (curRoom scalerCode?))
			(self perform: (curRoom scalerCode?))
		)
	)
	
	(method (dispose)
		(= regPathID (= checkCode (= okToCheck 0)))
		(if (IsObject scaler) (scaler dispose:))
		(= scaler 0)
		(super dispose:)
	)
)

(class rgCastle of Region
	(properties
		script 0
		number 0
		modNum -1
		noun 0
		timer 0
		keep 0
		initialized 0
		rFlag1 0
		rFlag2 0
		rFlag3 0
		dungeonEntered 0
		lastSeconds 0
		loiterTimer -1
		weddingRemind 0
		guardTimer 0
		guard2Timer 0
		guard1Code 0
		guard2Code 0
		stopTimers 0
		weddingMusicCount -1
	)
	
	(method (doit &tmp theLastSeconds)
		(= theLastSeconds (GetTime 1))
		(cond 
			(
				(and
					(not fastCast)
					(not (& (theIconBar state?) $0020))
					(!= lastSeconds theLastSeconds)
					(== curRoomNum newRoomNum)
					(not stopTimers)
				)
				(= lastSeconds theLastSeconds)
				(if
					(and
						(> loiterTimer 0)
						(not (curRoom script?))
						(== (-- loiterTimer) 0)
					)
					(self doLoiter:)
				)
				(if
				(and (> weddingRemind 0) (== (-- weddingRemind) 0))
					(if
						(or
							weddingMusicCount
							(and
								(== (regions size?) 3)
								(not ((ScriptID 81 0) tstFlag: 709 1))
								(not ((ScriptID 81 0) tstFlag: 709 2))
							)
							1
						)
						(if (!= (theMusic number?) 701)
							(theMusic fadeTo: 701 -1)
						)
						(= rFlag1 (| rFlag1 $0002))
						(++ weddingMusicCount)
						(curRoom warnUser: 1)
					else
						(= weddingRemind 5)
					)
				)
				(if (and (> guardTimer 0) (== (-- guardTimer) 0))
					(cond 
						((not (& rFlag2 $0001))
							(= rFlag2 (| rFlag2 $0001))
							(if (OneOf curRoomNum 850 880 781)
								(curRoom warnUser: 2)
							)
						)
						((== curRoomNum 850) (theGame handsOff:) (curRoom spotEgo: (ScriptID 80 5)))
					)
				)
				(if
				(and (> guard2Timer 0) (== (-- guard2Timer) 0))
					(cond 
						((not (& rFlag2 $0020))
							(= rFlag2 (| rFlag2 $0020))
							(if (== curRoomNum 870) (curRoom warnUser: 3 0))
						)
						((== curRoomNum 870) (theGame handsOff:) (curRoom warnUser: 3 1))
					)
				)
			)
			((& (theIconBar state?) $0020) (= lastSeconds theLastSeconds))
		)
		(super doit:)
	)
	
	(method (newRoom n)
		((ScriptID 80 0)
			keep:
				(OneOf
					n
					700
					710
					720
					730
					740
					750
					760
					770
					780
					781
					790
					800
					810
					820
					840
					850
					860
					870
					880
					180
					743
				)
		)
		(= initialized 0)
		(= loiterTimer -1)
		(super newRoom: n &rest)
		(= guard1Code (= guard2Code 0))
	)
	
	(method (doLoiter)
		(= loiterTimer 0)
		(curRoom doLoiter:)
	)
	
	(method (setupGuards)
		(if (cast contains: guard1)
			(guard1 checkCode: guard1Code)
			(if (not (IsObject (guard1 scaler?)))
				(guard1
					setScale:
						Scaler
						(curRoom maxScaleSize?)
						(curRoom minScaleSize?)
						(curRoom maxScaleY?)
						(curRoom minScaleY?)
				)
				((guard1 scaler?) doit:)
			)
		)
		(if (cast contains: guard2)
			(guard2 checkCode: guard2Code)
			(if (not (IsObject (guard2 scaler?)))
				(guard2
					setScale:
						Scaler
						(curRoom maxScaleSize?)
						(curRoom minScaleSize?)
						(curRoom maxScaleY?)
						(curRoom minScaleY?)
				)
				((guard2 scaler?) doit:)
			)
		)
	)
	
	(method (setFlag param1 &tmp temp0 temp1)
		(if argc (= temp0 [param1 0]))
		(= temp1 1)
		(while (< temp1 argc)
			(self temp0: (| (self temp0?) [param1 temp1]))
			(++ temp1)
		)
	)
	
	(method (clrFlag param1 &tmp temp0 temp1)
		(if argc (= temp0 [param1 0]))
		(= temp1 1)
		(while (< temp1 argc)
			(self temp0: (& (self temp0?) (~ [param1 temp1])))
			(++ temp1)
		)
	)
	
	(method (tstFlag param1 param2)
		(return (if (& (self param1?) param2) 1 else 0))
	)
)

(instance guardsGetEgo of Script
	(properties)
	
	(method (dispose)
		(rgCastle
			rFlag1: (| (rgCastle rFlag1?) $2000)
			dungeonEntered: 3
		)
		(= register 0)
		(super dispose:)
	)
	
	(method (changeState newState &tmp theGuard2)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego ignoreActors: setMotion: 0)
				(if
					(and
						(not (& (ego signal?) $0800))
						(not (ego facingMe: client))
					)
					(Face ego client self)
				else
					(= cycles 2)
				)
			)
			(1 (= cycles 4))
			(2
				(localproc_0a07 client 8 5)
				(client
					setSpeed: 3
					ignoreHorizon: 1
					ignoreActors: 1
					setMotion: PolyPath local0 local1 self
				)
				(if (curRoom moveOtherGuard?)
					(switch client
						(guard1 (= theGuard2 guard2))
						(guard2 (= theGuard2 guard1))
					)
					(localproc_0a07 theGuard2 25 0)
					(theGuard2
						setSpeed: 3
						ignoreHorizon: 1
						ignoreActors: 1
						setMotion: PolyPath local0 local1 self
					)
				)
			)
			(3
				(if (curRoom moveOtherGuard?) 0 else (= ticks 1))
			)
			(4
				(if (curRoom moveOtherGuard?)
					(switch client
						(guard1 (= theGuard2 guard2))
						(guard2 (= theGuard2 guard1))
					)
					(Face theGuard2 ego self)
				else
					(self cue:)
				)
			)
			(5
				(if register (self dispose:) else (= cycles 1))
			)
			(6
				(theGlobalSound fade:)
				(curRoom newRoom: 820)
			)
		)
	)
)

(instance guard1 of GuardDog
	(properties
		view 724
	)
	
	(method (init)
		(super init: &rest)
		(if (== (regions size?) 3)
			(= checkCode ((ScriptID 81 0) guard1Code?))
		)
	)
	
	(method (cue param1 &tmp theOkToCheck)
		(cond 
			(argc
				(= theOkToCheck okToCheck)
				(= okToCheck
					(if (>= 10 [param1 0]) (>= [param1 0] 4) else 0)
				)
				(if (and theOkToCheck (> param1 10))
					(curRoom warnUser: 4 (regPathID currentRoom?))
				)
			)
			((== (regions size?) 3) ((ScriptID 81 0) clrFlag: 709 1 loiterTimer: 36))
		)
	)
)

(instance guard2 of GuardDog
	(properties
		x 99
		y 181
		view 726
		loop 3
	)
	
	(method (init)
		(super init: &rest)
		(if (== (regions size?) 3)
			(= checkCode ((ScriptID 81 0) guard2Code?))
		)
	)
	
	(method (cue param1 &tmp theOkToCheck)
		(cond 
			(argc
				(= theOkToCheck okToCheck)
				(= okToCheck
					(if (>= 13 [param1 0]) (>= [param1 0] 3) else 0)
				)
				(if
					(and
						theOkToCheck
						(not okToCheck)
						(== (regPathID currentRoom?) 840)
					)
					(curRoom warnUser: 4 (regPathID currentRoom?))
				)
			)
			((== (regions size?) 3)
				((ScriptID 81 0)
					rFlag1: (& ((ScriptID 81 0) rFlag1?) $fffd)
					loiterTimer: 36
				)
			)
		)
	)
)
