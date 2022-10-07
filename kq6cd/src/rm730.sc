;;; Sierra Script 1.0 - (do not remove this comment)
(script# 730)
(include sci.sh)
(use Main)
(use CastleRoom)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Reverse)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm730 0
	kitchenDoor 1
	basementDoor 2
	proc730_3 3
)

(local
	local0
	local1
	local2
	local3
)
(procedure (proc730_3)
	(curRoom
		vanishingX: 160
		vanishingY: -10000
		maxScaleSize: 100
		minScaleSize: 60
	)
	(if local0
		(curRoom maxScaleY: 127 minScaleY: 68 minScaleSize: 64)
	else
		(curRoom maxScaleY: 174 minScaleY: 127)
	)
	(ego
		setScale:
			Scaler
			(curRoom maxScaleSize?)
			(curRoom minScaleSize?)
			(curRoom maxScaleY?)
			(curRoom minScaleY?)
	)
)

(procedure (localproc_1abb param1 param2 param3 &tmp temp0)
	(= temp0 param3)
	(while (& param1 (OnControl 4 param2 temp0))
		(++ temp0)
	)
	(return temp0)
)

(instance rm730 of CastleRoom
	(properties
		noun 3
		picture 730
		style $000a
		horizon 0
	)
	
	(method (init)
		(Load rsVIEW 730)
		(if ((ScriptID 80 0) tstFlag: 709 2)
			0
		else
			(LoadMany 128 794 736)
		)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						19
						170
						9
						158
						12
						141
						75
						69
						53
						69
						0
						109
						-50
						110
						-50
						-60
						369
						-60
						319
						114
						263
						65
						246
						65
						315
						146
						312
						162
						286
						178
						212
						129
						109
						129
						90
						142
						77
						142
						80
						149
						37
						179
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 176 134 196 134 200 141 176 141
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 126 134 145 134 145 141 122 141
					yourself:
				)
		)
		(features
			add: urns chandelier balconyFtr pillarsFtr stairs
			eachElementDo: #init
		)
		((ScriptID 80 5) noun: 4)
		((ScriptID 80 6) noun: 4)
		(super init: &rest)
		(ego init:)
		(candles setCycle: Forward init:)
		(switch prevRoomNum
			(740
				(ego
					posn: (throneDoor approachX?) (throneDoor approachY?)
				)
			)
			(860
				(= local0 1)
				(ego posn: 272 84)
			)
			(850
				(= local0 1)
				(ego posn: 52 84)
				(cond 
					(((ScriptID 80 0) tstFlag: 711 16384)
						((ScriptID 80 0) clrFlag: 711 16384)
						((ScriptID 80 5)
							init:
							loop: 0
							posn: 107 68
							setPri: 0
							ignoreActors:
							setScale: Scaler 35 64 107 59 1
						)
						((ScriptID 80 6)
							init:
							loop: 3
							posn: 111 68
							setPri: 0
							ignoreActors:
							setScale: Scaler 35 64 111 69 1
						)
						(ego posn: 46 83)
						(theMusic fade: 80 25 10 0)
						(self setScript: followEgoIn)
					)
					((= local2 ((ScriptID 80 0) tstFlag: 709 512))
						((ScriptID 80 5) init: loop: 0 posn: 82 165)
						((ScriptID 80 6) init: loop: 3 posn: 103 176)
						((ScriptID 80 6)
							signal: (& ((ScriptID 80 6) signal?) $efff)
						)
						(saladin init: loop: 4 cel: 1 stopUpd:)
						(cond 
							(((ScriptID 80 0) tstFlag: 710 1)
								((ScriptID 80 5) posn: 9 113)
								((ScriptID 80 6) posn: 18 128)
								(self spotEgo: (ScriptID 80 5))
							)
							((not ((ScriptID 80 0) tstFlag: 709 2048)) (ego posn: 51 83) (self setScript: overseeGuards))
							(else (self setScript: tooManyTimes))
						)
					)
				)
			)
			(840
				(ego setSpeed: 8 posn: 261 144)
				(self setScript: (ScriptID 732 0))
			)
			(else 
				(theMusic fadeTo: 700 -1)
				(ego reset: 3 733 posn: 229 181)
				((ScriptID 80 0) setFlag: 709 128)
				(self setScript: (ScriptID 731 0))
			)
		)
		(kitchenDoor init: stopUpd: ignoreActors:)
		(throneDoor
			init:
			ignoreActors:
			setPri: 1
			cel: ((ScriptID 80 0) tstFlag: 709 2)
			stopUpd:
		)
		(basementDoor init: stopUpd: ignoreActors:)
		(if ((ScriptID 80 0) tstFlag: 709 2)
			(if script
				(script next: saladinEnters)
			else
				(self setScript: saladinEnters)
			)
		)
		(proc730_3)
		((ScriptID 80 0) setupGuards:)
		(if (cast contains: saladin)
			(saladin
				setScale: Scaler maxScaleSize minScaleSize maxScaleY minScaleY
			)
			((saladin scaler?) doit:)
		)
		(if (ego scaler?) ((ego scaler?) doit:))
		(if (not script) (theGame handsOn:))
	)
	
	(method (doit)
		(= local1 (ego onControl: 1))
		(cond 
			(script 0)
			((== ((User alterEgo?) edgeHit?) 3) (ego y: (- (ego y?) 2)) (self setScript: don_tLeave))
			((& local1 $4000) (curRoom newRoom: 860))
			((& local1 $2000) (curRoom newRoom: 850))
			((& local1 $0800) (curRoom newRoom: 840))
		)
		(if (!= (ego view?) 881)
			(if (and (& local1 $0200) local0)
				(= local0 0)
				(proc730_3)
			)
			(if (and (& local1 $0400) (not local0))
				(= local0 1)
				(proc730_3)
			)
		)
		(if
		(and (not (& local1 $0600)) (== vanishingY -10000))
			(if local0 (= vanishingY -7) else (= vanishingY 92))
		)
		(super doit: &rest)
	)
	
	(method (setScript newScript)
		(if
			(and
				(== script saladinEnters)
				(!= newScript showLetter)
			)
			(theGame handsOff:)
			(saladinEnters cue:)
		else
			(super setScript: newScript &rest)
		)
	)
)

(instance don_tLeave of Script
	(properties
		name "don'tLeave"
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 4)
			)
			(1
				(messager say: 3 3 14 0 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance doKitchen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not ((ScriptID 80 0) tstFlag: 711 -32768))
					((ScriptID 80 0) setFlag: 711 -32768)
					(messager say: 8 5 0 1 self)
				else
					(= cycles 1)
				)
			)
			(1
				(soundFx2 number: 901 loop: 1 play:)
				(soundFx number: 731 loop: -1 play:)
				(kitchenDoor setCycle: EndLoop self)
			)
			(2
				(soundFx2 stop:)
				(ego setHeading: 270 setMotion: MoveTo 57 143 self)
			)
			(3 (messager say: 8 5 0 2 self))
			(4
				(ego
					setMotion: MoveTo (kitchenDoor approachX?) (kitchenDoor approachY?) self
				)
			)
			(5
				(soundFx stop:)
				(kitchenDoor setCycle: BegLoop self)
			)
			(6
				(messager say: 8 5 0 3 self oneOnly: 0)
			)
			(7
				(soundFx2 number: 902 loop: 1 play:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance overseeGuards of Script
	(properties)
	
	(method (dispose)
		((ScriptID 80 0) setFlag: 709 2048)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 1015 6) talkWidth: 150 x: 15 y: 20)
				((ScriptID 1015 7) talkWidth: 135 x: 160 y: 20)
				(= cycles 10)
			)
			(1 (messager say: 1 0 3 0 self))
			(2 (= seconds 2))
			(3
				(theMusic number: 710 loop: -1 play:)
				(if (Btst 72) (= register 5) else (= register 4))
				(messager say: 1 0 register 1 self)
			)
			(4
				((ScriptID 80 5) setScript: (ScriptID 80 4) self 1)
			)
			(5
				(ego
					setSpeed: 8
					ignoreActors:
					setMotion: PolyPath (- (saladin x?) 45) (saladin y?) self
				)
				(= seconds 3)
			)
			(6
				((ScriptID 80 5)
					setSpeed: 8
					ignoreActors:
					setMotion: PolyPath (- (saladin x?) 45) ((ScriptID 80 6) y?) self
				)
			)
			(7 (ego setHeading: 90))
			(8
				(messager say: 1 0 register 2 self oneOnly: 0)
			)
			(9 (curRoom newRoom: 820))
		)
	)
)

(instance tooManyTimes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 4)
			)
			(1
				(= register (if (Btst 72) 5 else 4))
				(messager say: 1 0 register 1 self)
			)
			(2
				((ScriptID 80 5) setScript: (ScriptID 80 4) self 1)
			)
			(3
				(messager say: 1 0 register 2 self oneOnly: 0)
			)
			(4
				((ScriptID 80 0) setFlag: 709 8192)
				(curRoom newRoom: 820)
			)
		)
	)
)

(instance saladinEnters of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(walkHandler add: self)
		(directionHandler add: self)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(directionHandler delete: self)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 80 0) weddingRemind: 0)
				(if (== prevRoomNum 840)
					(self setScript: exitBaseScr self)
				else
					(self setScript: exitUpstairsScr self)
				)
			)
			(1
				(ego setMotion: PolyPath 150 128 self)
			)
			(2 (ego setHeading: 45 self))
			(3 (= cycles 5))
			(4
				(saladin
					view: 737
					setLoop: 0
					cel: 0
					posn: 160 123
					setPri: 0
					sightAngle: 180
					init:
				)
				(throneDoor startUpd: cel: 2 stopUpd:)
				(ego
					setCycle: Reverse
					setLoop: -1
					setLoop: 6
					setMotion: MoveTo 141 131 self
				)
			)
			(5
				(ego setCycle: StopWalk -1)
				(saladin setCycle: CycleTo 2 1 self)
			)
			(6
				(DisposeScript 969)
				(soundFx2 number: 901 loop: 1 play:)
				(saladin setCycle: EndLoop self)
				(throneDoor setCycle: EndLoop)
			)
			(7 (= cycles 20))
			(8
				(soundFx2 stop:)
				(saladin setLoop: 7 cel: 9 setPri: 9 setCycle: BegLoop self)
				(soundFx2 number: 652 setLoop: 1 play:)
				(throneDoor setCycle: BegLoop throneDoor)
			)
			(9
				(= register (if (Btst 72) 11 else 10))
				(messager say: 1 0 register 0 self)
			)
			(10
				(theGame handsOn:)
				(if (HaveMouse) (= seconds 11) else (= seconds 21))
			)
			(11
				(theGame handsOff:)
				(= register (if (Btst 72) 13 else 12))
				(messager say: 1 0 register 0 self)
			)
			(12
				(ego hide:)
				(saladin
					setLoop: 1
					cel: 0
					posn: 140 133
					setCycle: EndLoop self
				)
			)
			(13
				(ego
					view: 737
					normal: 0
					setLoop: 2
					cel: 0
					x: 121
					y: 134
					setScale: 0
					setCycle: Walk
					setSpeed: 8
					setStep: 3 2
					show:
					setMotion: MoveTo 99 135 self
				)
				(saladin
					setLoop: 3
					cel: 0
					posn: 149 134
					setSpeed: 8
					setStep: 3 2
					setCycle: Walk
					setMotion: MoveTo 128 134
				)
			)
			(14
				(ego setLoop: 5 cel: 0)
				(saladin setMotion: MoveTo 128 134 self)
			)
			(15
				(theMusic number: 705 setLoop: 1 play:)
				(saladin setLoop: 4 cel: 0 setCycle: EndLoop self)
				(theGlobalSound number: 756 setLoop: 1 play:)
			)
			(16
				(saladin cycleSpeed: 8 setCycle: CycleTo 2 -1 self)
			)
			(17
				(ego
					setLoop: 8
					cel: 0
					cycleSpeed: 5
					setCycle: CycleTo 3 1 self
				)
			)
			(18 (= cycles 10))
			(19
				(theGlobalSound number: 971 setLoop: 1 play: self)
				(ego cycleSpeed: 8 setCycle: EndLoop self)
			)
			(20 (EgoDead 32))
		)
	)
	
	(method (handleEvent event)
		(if
			(and
				(user canInput:)
				(or
					(& (event type?) evMOVE)
					(and
						(& (event type?) evJOYSTICK)
						(!= (event message?) JOY_NULL)
					)
				)
			)
			(event claimed: 1)
			(self cue:)
		)
		(event claimed?)
	)
)

(instance exitBaseScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 173 127 self)
			)
			(1 (ego setHeading: 225 self))
			(2
				(ego
					normal: 0
					view: 881
					loop: 2
					cel: 0
					x: 158
					y: 132
					setScale:
					scaleX: 90
					scaleY: 90
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(3 (= seconds 3))
			(4 (ego setCycle: BegLoop self))
			(5
				(ego posn: 173 127 reset: 5 setSpeed: 8)
				(= cycles 3)
			)
			(6 (self dispose:))
		)
	)
)

(instance exitUpstairsScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1 (messager say: 1 0 8 1 self))
			(2
				(ego setSpeed: 8 setMotion: PolyPath 19 166 self)
			)
			(3 (ego setHeading: 135 self))
			(4 (= cycles 4))
			(5
				(theGame handsOff:)
				(ego
					normal: 0
					setScale:
					scaleX: 144
					scaleY: 144
					view: 881
					loop: 3
					cel: 4
					x: 38
					y: 175
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(6 (messager say: 1 0 8 2 self))
			(7 (ego setCycle: BegLoop self))
			(8
				(ego reset: 4 posn: 19 166 setSpeed: 8)
				(= cycles 4)
			)
			(9 (self dispose:))
		)
	)
)

(instance showLetter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 80 0) setFlag: 709 1024)
				(theGame givePoints: 3)
				(roomConv add: -1 6 61 21 1 add: -1 6 61 21 2 init: self)
			)
			(1
				(ego hide:)
				(saladin
					setLoop: 6
					cel: 0
					setPri: 9
					posn: 140 133
					setCycle: EndLoop self
				)
			)
			(2
				(roomConv add: -1 6 61 21 3 init: self)
			)
			(3 (= seconds 3))
			(4
				(roomConv add: -1 6 61 21 4 init: self)
			)
			(5 (saladin setCycle: BegLoop self))
			(6
				(roomConv
					add: -1 6 61 21 5
					add: -1 6 61 21 6
					add: -1 6 61 21 7
					init: self
				)
			)
			(7
				(ego show:)
				(saladin
					setLoop: 7
					cel: 0
					posn: 160 123
					setCycle: EndLoop self
				)
				(soundFx2 number: 652 setLoop: 1 play:)
			)
			(8
				(roomConv add: -1 6 61 21 8 add: -1 6 61 21 9)
				(if (Btst 72)
					(roomConv add: -1 6 61 20 1)
				else
					(roomConv add: -1 6 61 21 10)
				)
				(roomConv init: self)
			)
			(9 (ego put: 20) (= cycles 1))
			(10
				(saladin
					view: 736
					loop: 4
					cel: 2
					scaleSignal: 1
					scaleX: 87
					scaleY: 87
					x: (+ (saladin x?) 1)
					y: (+ (saladin y?) 3)
				)
				(= cycles 8)
			)
			(11
				(saladin cel: 0)
				(= cycles 8)
			)
			(12
				(saladin cel: 3)
				(= cycles 8)
			)
			(13
				(throneDoor setCycle: EndLoop self)
				(soundFx2 number: 901 setLoop: 1 play:)
			)
			(14
				(saladin
					setLoop: 3
					setCycle: Walk
					setMotion: MoveTo 167 123 self
				)
			)
			(15
				(saladin loop: 4 cel: 3 stopUpd:)
				(ego setMotion: MoveTo 152 128 self)
			)
			(16
				(ego setMotion: MoveTo 152 122 self)
			)
			(17 (= cycles 4))
			(18 (curRoom newRoom: 740))
		)
	)
)

(instance enterThroneRoom of Script
	(properties)
	
	(method (doit)
		(if (& local1 $1000) (curRoom newRoom: 740))
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(soundFx2 number: 901 loop: 1 play:)
				(throneDoor setCycle: EndLoop self)
			)
			(1
				(soundFx2 stop:)
				(proc80_2 1)
			)
		)
	)
)

(instance followEgoIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(theMusic stop:)
				(theMusic number: 700 loop: -1 play: 80 fade: 127 25 10 0)
				(= cycles 10)
			)
			(2
				((ScriptID 80 5) setMotion: MoveTo 59 68 self)
				((ScriptID 80 6) setMotion: MoveTo 69 68 self)
			)
			(3 0)
			(4
				(theMusic number: 710 loop: -1 play:)
				(messager say: 1 0 2 1 self)
			)
			(5 (messager say: 1 0 2 2 self))
			(6
				(curRoom moveOtherGuard: 1)
				((ScriptID 80 5) setScript: (ScriptID 80 4) self 1)
			)
			(7
				(messager say: 1 0 2 3 self oneOnly: 0)
			)
			(8
				(theMusic fade:)
				(curRoom newRoom: 820)
			)
		)
	)
)

(instance tryToOpenDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 3)
			)
			(1
				(ego
					view: 730
					loop: 4
					cel: 0
					normal: 0
					setScale: 0
					scaleX: 128
					scaleY: 128
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(2 (ego setCycle: BegLoop self))
			(3 (messager say: 7 5 0 0 self))
			(4
				(ego reset: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance throneDoor of Prop
	(properties
		x 160
		y 100
		noun 9
		approachX 155
		approachY 129
		view 730
	)
	
	(method (init)
		(super init: &rest)
		(if
			(and
				(not local2)
				(not ((ScriptID 80 0) tstFlag: 709 2))
			)
			(self approachVerbs: 5)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (or (not local3) local2)
					(++ local3)
					(if
						(and
							(not local2)
							(not ((ScriptID 80 0) tstFlag: 709 2))
						)
						(= _approachVerbs
							(| _approachVerbs (approachCode doit: 1))
						)
					)
					(messager say: 9 1 25)
				else
					(messager say: noun theVerb 26 1 (ScriptID 82))
					(curRoom
						setScript: ((ScriptID 82) start: -1 yourself:) 0 lookIntoThroneRoom
					)
				)
			)
			(5
				(if local2
					(messager say: 7 5 22)
				else
					(curRoom setScript: enterThroneRoom)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe event)
		(return (if (super onMe: event) (<= (event y?) 127) else 0))
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance lookIntoThroneRoom of Script
	(properties)
	
	(method (dispose)
		(tempGuard1 dispose:)
		(tempGuard2 dispose:)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 82 1) noun: 5 init: 794)
				(tempGuard1 init: stopUpd:)
				(tempGuard2
					view: ((ScriptID 80 6) view?)
					init:
					stopUpd:
				)
				(= cycles 4)
			)
			(1 (messager say: 9 1 26 2))
		)
	)
)

(instance tempGuard1 of View
	(properties
		x 148
		y 128
		noun 5
		view 724
		loop 4
		priority 14
		signal $4010
		scaleSignal $0001
	)
)

(instance tempGuard2 of View
	(properties
		x 167
		y 126
		noun 5
		loop 4
		cel 1
		priority 14
		signal $4010
		scaleSignal $0001
	)
)

(instance kitchenDoor of Prop
	(properties
		x 70
		y 143
		z 28
		noun 8
		approachX 79
		approachY 142
		view 730
		loop 1
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(if
			(and
				(not local2)
				(not ((ScriptID 80 0) tstFlag: 709 2))
			)
			(self approachVerbs: 5)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if local2
					(messager say: 7 5 22)
				else
					(curRoom setScript: doKitchen)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance basementDoor of Prop
	(properties
		x 250
		y 119
		noun 7
		approachX 228
		approachY 144
		view 730
		loop 2
	)
	
	(method (init)
		(super init: &rest)
		(if
			(and
				(not local2)
				(not ((ScriptID 80 0) tstFlag: 709 2))
			)
			(self approachVerbs: 5)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if local2
					(messager say: 7 5 22)
				else
					(curRoom setScript: tryToOpenDoor)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance candles of Prop
	(properties
		x 161
		y 39
		onMeCheck $0000
		view 730
		loop 3
		priority 15
		signal $4010
	)
)

(instance saladin of Actor
	(properties
		x 115
		y 166
		noun 6
		view 736
		loop 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(cond 
			(local2
				(if (== theVerb 1)
					(if (Btst 72)
						(messager say: noun theVerb 18)
					else
						(messager say: noun theVerb 19)
					)
				else
					(messager say: 6 5 22)
				)
			)
			((== theVerb 61) (curRoom setScript: showLetter))
			((OneOf theVerb 1 2)
				(if (Btst 72)
					(messager say: noun theVerb 20)
				else
					(messager say: noun theVerb 21)
				)
			)
			((OneOf theVerb 5 8 18 65 33 16) (messager say: noun theVerb 23))
			(else (messager say: 6 0 23))
		)
	)
)

(instance urns of Feature
	(properties
		y 163
		onMeCheck $0002
	)
	
	(method (onMe event)
		(if (and (super onMe: event) (= x (event x?)))
			(if (and (> (event x?) 160) (= noun 13))
			else
				(= noun 12)
			)
		)
	)
)

(instance chandelier of Feature
	(properties
		x 160
		y 160
		noun 14
		onMeCheck $0004
	)
)

(instance balconyFtr of Feature
	(properties
		noun 16
		onMeCheck $0080
	)
)

(instance pillarsFtr of Feature
	(properties
		noun 15
		onMeCheck $0100
	)
	
	(method (onMe event)
		(= x (event x?))
		(= y 0)
		(if
			(or
				(super onMe: event)
				(InRect 50 59 66 70 event)
				(InRect 254 62 271 70 event)
			)
			(= y (localproc_1abb 256 x 116))
		)
	)
)

(instance stairs of Feature
	(properties
		noun 11
		onMeCheck $0410
	)
	
	(method (onMe event)
		(= x (= y 0))
		(if (and (super onMe: event) (= x (event x?)))
			(= y (event y?))
		)
	)
)

(instance roomConv of Conversation
	(properties)
)
