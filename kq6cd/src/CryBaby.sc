;;; Sierra Script 1.0 - (do not remove this comment)
(script# 481)
(include sci.sh)
(use Main)
(use Kq6Sound)
(use Kq6Procs)
(use PolyPath)
(use Motion)
(use Actor)
(use System)

(public
	proc481_0 0
	proc481_1 1
	takeBottleAway 2
	proc481_3 3
	cryMusic 4
	suckMusic 5
	proc481_6 6
	proc481_7 7
)

(local
	local0
	local1
	local2
	local3
)
(procedure (proc481_0)
	((ScriptID 480 5) register: 1)
	(ego setScript: takeBottleAway 0 (ScriptID 480 6))
)

(procedure (proc481_1 param1 &tmp temp0)
	((ScriptID 480 5) register: 1)
	(if (not (Btst 118))
		(Bset 118)
		(theGame givePoints: 2)
	)
	(switch param1
		(1
			(= local2 0)
			(= local3 12)
			((ScriptID 40 0) bottleSucker: 1)
			(ego setScript: giveBabyBottle 0 babyFace1)
		)
		(2
			(= local2 1)
			(= local3 11)
			((ScriptID 40 0) bottleSucker: 2)
			(ego setScript: giveBabyBottle 0 babyFace2)
		)
		(3
			(= local2 0)
			(= local3 9)
			((ScriptID 40 0) bottleSucker: 3)
			(ego setScript: giveBabyBottle 0 babyFace3)
		)
		(4
			(= local2 0)
			(= local3 7)
			((ScriptID 40 0) bottleSucker: 4)
			(curRoom setScript: giveBabyBottle 0 babyFace4)
		)
	)
)

(procedure (proc481_3 param1 &tmp temp0)
	((ScriptID 480 5) register: 1)
	(switch param1
		(1
			(ego setScript: getBabyTears 0 babyFace1)
		)
		(2
			(ego setScript: getBabyTears 0 babyFace2)
		)
		(3
			(ego setScript: getBabyTears 0 babyFace3)
		)
		(4
			(ego setScript: getBabyTears 0 babyFace4)
		)
	)
)

(procedure (proc481_6)
	(cryMusic stop: dispose:)
	(suckMusic stop: dispose:)
	(if (cast contains: babyFace1)
		(babyFace1 setCycle: 0 dispose: delete:)
	)
	(if (cast contains: babyFace2)
		(babyFace2 setCycle: 0 dispose: delete:)
	)
	(if (cast contains: babyFace3)
		(babyFace3 setCycle: 0 dispose: delete:)
	)
	(if (cast contains: babyFace4)
		(babyFace4 setCycle: 0 dispose: delete:)
	)
	(DisposeScript 481)
)

(procedure (proc481_7)
	(if
		(!=
			(babyFace1 bottleNum?)
			((ScriptID 40 0) bottleSucker?)
		)
		(babyFace1 init: setCycle: Fwd)
	)
	(if
		(!=
			(babyFace2 bottleNum?)
			((ScriptID 40 0) bottleSucker?)
		)
		(babyFace2 init: setCycle: Fwd)
	)
	(if
		(!=
			(babyFace3 bottleNum?)
			((ScriptID 40 0) bottleSucker?)
		)
		(babyFace3 init: setCycle: Fwd)
	)
	(if
		(!=
			(babyFace4 bottleNum?)
			((ScriptID 40 0) bottleSucker?)
		)
		(babyFace4 init: setCycle: Fwd)
	)
	(cryMusic setLoop: -1 play:)
	(suckMusic setLoop: -1 play:)
)

(class CryBaby of Prop
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
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		walkToX 0
		walkToY 0
		stoopX 0
		stoopY 0
		bottleNum 0
	)
	
	(method (dispose)
		(cryMusic stop: dispose:)
		(suckMusic stop: dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 57 58 59 60 96)
			(if
			(== ((ScriptID 40 0) bottleSucker?) (self bottleNum?))
				(messager say: 9 56 17 0 0 480)
			else
				(messager say: 9 56 ((ScriptID 40 0) lampMsg?) 0 0 480)
			)
		else
			(switch theVerb
				(14
					(messager say: 9 14 0 1 0 480)
				)
				(5
					(cond 
						(
						(== ((ScriptID 40 0) bottleSucker?) (self bottleNum?)) (curRoom setScript: takeBottleAway 0 (ScriptID 480 6)))
						((== ((ScriptID 40 0) lampMsg?) 15) (messager say: 9 5 15 1 0 480))
						(else (messager say: 9 5 18 1 0 480))
					)
				)
				(43
					(cond 
						(
						(== ((ScriptID 40 0) bottleSucker?) (self bottleNum?)) (messager say: 9 43 17 1 0 480))
						((not (Btst 77)) (messager say: 9 43 21 1 0 480))
						((or (& global161 $0004) (Btst 144)) (messager say: 9 43 20 1 0 480))
						((& global161 $0001) (messager say: 9 43 13 1 0 480))
						((& global161 $0002) (curRoom setScript: getBabyTears 0 self))
						((== ((ScriptID 40 0) lampMsg?) 15) (messager say: 9 43 15 1 0 480))
						(else (curRoom setScript: getBabyTears 0 self))
					)
				)
				(1
					(if (== ((ScriptID 40 0) lampMsg?) 15)
						(messager say: 9 1 ((ScriptID 40 0) lampMsg?) 1 0 480)
					else
						(messager say: 9 1 16 1 0 480)
					)
				)
				(2
					(if (== ((ScriptID 40 0) lampMsg?) 15)
						(messager say: 9 2 ((ScriptID 40 0) lampMsg?) 0 0 480)
					else
						(messager say: 9 2 16 1 0 480)
					)
				)
				(44
					(cond 
						(
						(== ((ScriptID 40 0) bottleSucker?) (self bottleNum?)) (messager say: 9 44 17 1 0 480))
						((not (Btst 77)) (messager say: 9 44 21 1 0 480))
						(else (messager say: 9 44 22 1 0 480))
					)
				)
				(24
					(if (== ((ScriptID 40 0) lampMsg?) 15)
						(messager say: 9 24 15 1 0 480)
					else
						(messager say: 9 24 16 1 0 480)
					)
				)
				(else 
					(self setScript: inventOnBaby 0 self)
				)
			)
		)
	)
)

(instance inventOnBaby of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setMotion: PolyPath (register walkToX?) (register walkToY?) self
				)
			)
			(1
				(= temp0
					(GetAngle
						(ego x?)
						(ego y?)
						(register x?)
						(register y?)
					)
				)
				(ego setHeading: temp0 self)
			)
			(2 (= cycles 6))
			(3
				(messager say: 9 0 16 0 self 480)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance babyFace1 of CryBaby
	(properties
		x 51
		y 152
		noun 9
		modNum 480
		view 4803
		priority 12
		signal $4010
		walkToX 95
		walkToY 159
		stoopX 83
		stoopY 162
		bottleNum 1
	)
)

(instance babyFace2 of CryBaby
	(properties
		x 6
		y 147
		noun 9
		modNum 480
		view 4803
		loop 1
		priority 11
		signal $4010
		walkToX 55
		walkToY 153
		stoopX 36
		stoopY 157
		bottleNum 2
	)
)

(instance babyFace3 of CryBaby
	(properties
		x 35
		y 122
		noun 9
		modNum 480
		view 4803
		loop 2
		priority 9
		signal $4010
		walkToX 81
		walkToY 131
		stoopX 63
		stoopY 135
		bottleNum 3
	)
)

(instance babyFace4 of CryBaby
	(properties
		x 15
		y 107
		noun 9
		modNum 480
		view 4803
		loop 3
		priority 7
		signal $4010
		walkToX 62
		walkToY 116
		stoopX 43
		stoopY 119
		bottleNum 4
	)
)

(instance cryMusic of Kq6Sound
	(properties
		number 481
		loop -1
	)
	
	(method (play)
		(= local0 1)
		(super play:)
	)
	
	(method (stop)
		(= local0 0)
		(super stop:)
	)
	
	(method (check)
		(if
			(and
				local0
				(DoAudio 4)
				(== (DoAudio 6) -1)
				(not fastCast)
			)
			(self play:)
		)
		(super check:)
	)
)

(instance suckMusic of Kq6Sound
	(properties
		number 485
		loop -1
	)
)

(instance giveBabyBottle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (!= register babyFace2) (Bset 59))
				(ego
					setMotion: PolyPath (register walkToX?) (register walkToY?) self
				)
			)
			(1
				(ego
					view: 4811
					setLoop: 1
					cel: 0
					posn: (register stoopX?) (register stoopY?)
					normal: 0
					cycleSpeed: 12
					setCycle: End self
				)
				(UnLoad 128 900)
			)
			(2
				(messager say: 9 62 0 1 self 480)
			)
			(3
				(cryMusic setLoop: -1 play:)
				(suckMusic setLoop: -1 play:)
				((ScriptID 480 6)
					setLoop:
						(if
						(or (== register babyFace1) (== register babyFace2))
							1
						else
							0
						)
					x: (- (ego x?) 24)
					y: (- (ego y?) 10)
					z: 5
					setPri: local3
					init:
				)
				(if (Btst 77)
					((ScriptID 40 0) lampMsg: 22)
				else
					((ScriptID 40 0) lampMsg: 21)
				)
				(if (!= register babyFace1)
					(babyFace1 init: setCycle: Fwd)
				)
				(if (!= register babyFace2)
					(babyFace2 init: setCycle: Fwd)
				)
				(if (!= register babyFace3)
					(babyFace3 init: setCycle: Fwd)
				)
				(if (!= register babyFace4)
					(babyFace4 init: setCycle: Fwd)
				)
				(ego setLoop: 3 cycleSpeed: 3 setCycle: Beg self)
				(UnLoad 128 4811)
			)
			(4
				(ego
					posn: (register walkToX?) (register walkToY?)
					reset: 1
				)
				(= ticks 8)
			)
			(5
				(messager say: 9 62 0 2 self 480)
			)
			(6
				(ego setMotion: PolyPath 135 170 self)
			)
			(7
				(messager say: 9 62 0 3 self 480)
			)
			(8
				(cryMusic setLoop: -1 play:)
				(theGame handsOn:)
				(Bclr 59)
				(register hide:)
				(self dispose:)
			)
		)
	)
)

(instance getBabyTears of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setMotion: PolyPath (register walkToX?) (register walkToY?) self
				)
			)
			(1
				(ego
					view: 4811
					setLoop: 0
					cel: 0
					posn: (register stoopX?) (register stoopY?)
					normal: 0
					cycleSpeed: 6
					setCycle: End self
				)
				(UnLoad 128 900)
			)
			(2 (= cycles 2))
			(3 (ego setCycle: Beg self))
			(4
				(= global161 (| global161 $0004))
				(theGame givePoints: 1)
				(ego
					reset: 1
					posn: (register walkToX?) (register walkToY?)
				)
				(UnLoad 128 4811)
				(= ticks 8)
			)
			(5
				(if (& global161 $0002)
					(messager say: 9 43 14 1 self 480)
				else
					(messager say: 9 43 19 1 self 480)
				)
			)
			(6
				(ego setMotion: PolyPath 135 170 self)
			)
			(7
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance takeBottleAway of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setMotion:
						PolyPath
						(+ ((ScriptID 480 6) x?) 45)
						(+ ((ScriptID 480 6) y?) 1)
						self
				)
			)
			(1
				(ego
					view: 4811
					setLoop: 3
					setPri: (if local2 -1 else (+ ((ScriptID 480 6) priority?) 1))
					cel: 0
					posn: (+ ((ScriptID 480 6) x?) 21) (+ ((ScriptID 480 6) y?) 6)
					normal: 0
					cycleSpeed: 12
					setCycle: End self
				)
				(UnLoad 128 900)
			)
			(2
				(messager say: 9 5 17 1 self 480)
			)
			(3 (ego setCycle: Beg self))
			(4
				(if (== ((ScriptID 40 0) bottleSucker?) 3)
					(= temp0 10)
				else
					(= temp0 1)
				)
				(ego
					reset: 1
					setPri: 15
					posn: (+ ((ScriptID 480 6) x?) 45) (+ ((ScriptID 480 6) y?) temp0)
					setMotion: PolyPath 135 170 self
				)
				(UnLoad 128 4811)
			)
			(5
				(theGame handsOn:)
				(ego setPri: -1)
				(self dispose:)
			)
		)
	)
)
