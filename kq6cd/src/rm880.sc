;;; Sierra Script 1.0 - (do not remove this comment)
(script# 880)
(include sci.sh)
(use Main)
(use CastleRoom)
(use KQ6Print)
(use Kq6Procs)
(use Inset)
(use Conv)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use DPath)
(use Motion)
(use Actor)
(use System)

(public
	rm880 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
	local7
)
(instance rm880 of CastleRoom
	(properties
		noun 3
		picture 880
		horizon 0
		vanishingX 318
		vanishingY 69
		minScaleSize 95
	)
	
	(method (init)
		(LoadMany 128 882 881 880)
		(if
			(and
				((ScriptID 80 0) tstFlag: 711 512)
				(not ((ScriptID 80 0) tstFlag: 710 1))
				(not ((ScriptID 80 0) tstFlag: 709 2))
			)
			((ScriptID 80 0) guardTimer: 0 setFlag: 710 1)
		)
		(self
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						62
						70
						62
						135
						93
						143
						117
						148
						155
						148
						190
						149
						221
						143
						234
						134
						234
						125
						224
						116
						201
						108
						181
						104
						181
						70
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 100 118 119 115 132 113 143 117 143 125 110 125
					yourself:
				)
		)
		(if (<= (ego y?) 136)
			(ego posn: 170 123)
		else
			(ego posn: 98 128)
		)
		(ego
			init:
			reset: 0
			setScale: Scaler maxScaleSize minScaleSize maxScaleY minScaleY
		)
		(= local0 (OneOf ((inventory at: 26) owner?) 880 ego))
		(features add: roomFeatures pillar eachElementDo: #init)
		(portrait init: stopUpd:)
		(if (== ((inventory at: 27) owner?) 850) (bird init:))
		(if (== ((inventory at: 26) owner?) 880) (nail init:))
		(super init: &rest)
		((ScriptID 1015 6) talkWidth: 150 x: 15 y: 20)
		((ScriptID 1015 7) talkWidth: 135 x: 160 y: 20)
		(cond 
			(((ScriptID 80 0) tstFlag: 709 256)
				((ScriptID 80 0) clrFlag: 709 256)
				(self setScript: enterRoomScr 0 guardsTakeBird)
			)
			(((ScriptID 80 0) tstFlag: 710 1) (self setScript: enterRoomScr 0 watchGuardsComeBack))
			((!= ((inventory at: 27) owner?) 730)
				((ScriptID 80 5)
					init:
					setPri: 5
					ignoreActors:
					setScale:
						Scaler
						(curRoom maxScaleSize?)
						(curRoom minScaleSize?)
						(curRoom maxScaleY?)
						(curRoom minScaleY?)
					posn: 200 87
					setScript: guardsPatrol
				)
				((ScriptID 80 6)
					init:
					setPri: 5
					ignoreActors:
					setScale:
						Scaler
						(curRoom maxScaleSize?)
						(curRoom minScaleSize?)
						(curRoom maxScaleY?)
						(curRoom minScaleY?)
					posn: 212 87
				)
			)
			(else 0)
		)
		(if (not script) (theGame handsOn:))
		((ScriptID 80 0) clrFlag: 711 256)
	)
	
	(method (doit)
		(if (& (= local6 (ego onControl: 1)) $4002)
			(self newRoom: 850)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		((ScriptID 80 5) setScript: 0 setPri: -1)
		((ScriptID 80 6) setPri: -1)
		(super dispose:)
	)
	
	(method (setScript newScript &tmp theCaller)
		(if (IsObject newScript)
			(if (hideEgo caller?)
				(waitForEgoToHide
					register: (+ (waitForEgoToHide register?) 1)
				)
				(newScript next: waitForEgoToHide)
				(= theCaller (hideEgo caller?))
				(theCaller setScript: newScript &rest)
			else
				(super setScript: newScript &rest)
			)
		else
			(super setScript: newScript &rest)
		)
	)
	
	(method (scriptCheck &tmp temp0)
		(= temp0 0)
		(if (!= ((inventory at: 27) owner?) 730)
			(messager say: 0 0 112 0 0 899)
		else
			(= temp0 1)
		)
		(return temp0)
	)
	
	(method (warnUser param1)
		(switch param1
			(2
				(KQ6Print posn: -1 10 font: userFont say: 0 1 0 3 1 init:)
				(watchGuardsComeBack start: -1)
				(if (not script)
					(self setScript: watchGuardsComeBack)
				else
					(if (theDoits contains: portraitInset)
						(portraitInset dispose:)
					)
					(script next: watchGuardsComeBack)
				)
				(hideEgo caller: watchGuardsComeBack)
				((hideEgo caller?) setScript: waitForEgoToHide)
			)
			(1
				(messager say: 1 0 5 1)
				(if (not script)
					(self setScript: waitedToLongToEscape)
				else
					(script next: waitedToLongToEscape)
					(if (theDoits contains: portraitInset)
						(portraitInset dispose:)
					)
				)
			)
			(else 
				(super warnUser: param1 &rest)
			)
		)
	)
)

(instance enterRoomScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(switch register
					(guardsTakeBird
						((ScriptID 80 0) setFlag: 711 128)
						(messager say: 1 0 2 1 self)
					)
					(watchGuardsComeBack
						(messager say: 1 0 3 1 self)
					)
					(else  (= cycles 1))
				)
			)
			(2
				(register start: -1)
				(curRoom setScript: register)
				(hideEgo caller: register)
				(theGame handsOn:)
				((hideEgo caller?) setScript: waitForEgoToHide)
			)
		)
	)
)

(instance waitedToLongToEscape of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 964)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 80 0) setFlag: 711 128)
				(= cycles 4)
			)
			(1 (messager say: 1 0 5 2 self))
			(2 (messager say: 1 0 5 3 self))
			(3
				(self setScript: hideEgo self 1)
			)
			(4 (= seconds 2))
			(5
				((ScriptID 80 5) setMotion: MoveTo 108 98)
				((ScriptID 80 6) setMotion: MoveTo 79 114 self)
			)
			(6 (messager say: 1 0 5 4 self))
			(7
				(Face (ScriptID 80 6) (ScriptID 80 5) self)
			)
			(8 (messager say: 1 0 5 5 self))
			(9
				((ScriptID 80 5) setPri: -1)
				((ScriptID 80 6) setPri: -1)
				((ScriptID 80 5) setMotion: MoveTo 161 111 self)
				((ScriptID 80 6) setMotion: DPath 94 129 122 128)
			)
			(10
				(messager say: 1 0 5 6 self)
			)
			(11
				(messager say: 1 0 5 7 self)
			)
			(12
				(self setScript: hideEgo self)
			)
			(13
				(messager say: 1 0 5 8 self oneOnly: 0)
			)
			(14
				(curRoom moveOtherGuard: 1)
				(curRoom spotEgo: (proc80_7))
			)
		)
	)
)

(instance walkGuardsOutPartial of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(if (not register)
			(if ((ScriptID 80 0) tstFlag: 711 128)
				((ScriptID 80 5) posn: 200 87)
				((ScriptID 80 6) posn: 212 87)
			else
				((ScriptID 80 5) posn: 37 113)
				((ScriptID 80 6) posn: 57 120)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (>= (ego x?) 126)
					(= local2 159)
					(= local3 94)
					(= local4 169)
					(= local5 98)
				else
					(= local2 93)
					(= local3 105)
					(= local4 112)
					(= local5 108)
				)
				(= cycles 1)
			)
			(1
				((ScriptID 80 5)
					init:
					setPri: 5
					setScale:
						Scaler
						(curRoom maxScaleSize?)
						(curRoom minScaleSize?)
						(curRoom maxScaleY?)
						(curRoom minScaleY?)
					setMotion: MoveTo local2 local3
				)
				((ScriptID 80 6)
					init:
					setPri: 5
					setScale:
						Scaler
						(curRoom maxScaleSize?)
						(curRoom minScaleSize?)
						(curRoom maxScaleY?)
						(curRoom minScaleY?)
					setMotion: MoveTo local4 local5 self
				)
			)
			(2
				((ScriptID 80 5) setPri: -1)
				((ScriptID 80 6) setPri: -1)
				(= register 0)
				(self dispose:)
			)
		)
	)
)

(instance takeDownPicture of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 4 5 10 0 self)
			)
			(1
				(nail init:)
				(portrait hide:)
				(ego
					view: 882
					setScale: 0
					scaleX: 128
					scaleY: 128
					loop: 1
					cel: 0
					x: 216
					y: 118
					cycleSpeed: 8
					normal: 0
					setCycle: EndLoop self
				)
			)
			(2
				(portrait show: posn: 214 111 stopUpd:)
				(ego loop: 2 cel: 0 x: 216 y: 118 setCycle: EndLoop self)
			)
			(3
				(if (or (== next waitForEgoToHide) (not next))
					(theGame handsOn:)
				)
				(if (not (Bset 87)) (theGame givePoints: 3))
				(= local0 1)
				(ego reset: 6 posn: 205 112)
				(self dispose:)
			)
		)
	)
)

(instance replacePicture of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 4 5 12 0 self)
			)
			(1
				(ego
					view: 882
					setScale: 0
					scaleX: 128
					scaleY: 128
					loop: 2
					cel: 4
					x: 216
					y: 118
					cycleSpeed: 8
					normal: 0
					setCycle: BegLoop self
				)
			)
			(2
				(portrait hide:)
				(ego loop: 1 cel: 6 x: 216 y: 118 setCycle: BegLoop self)
			)
			(3
				(portrait posn: 223 87 show: stopUpd:)
				(nail dispose:)
				((inventory at: 26) owner: 0)
				(if (or (== next waitForEgoToHide) (not next))
					(theGame handsOn:)
				)
				(= local0 0)
				(ego reset: 6 posn: 205 112)
				(self dispose:)
			)
		)
	)
)

(instance guardsTakeBird of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theGame givePoints: 2)
				((ScriptID 80 0) clrFlag: 711 128)
				(self setScript: leaveConvScr self)
			)
			(1
				(roomConv add: -1 1 0 2 2 add: -1 1 0 2 3 init: self)
			)
			(2
				(self setScript: leaveConvScr self)
			)
			(3
				(roomConv
					add: -1 1 0 2 4
					add: -1 1 0 2 5
					add: -1 1 0 2 6
					add: -1 1 0 2 7
					add: -1 1 0 2 8
					add: -1 1 0 2 9
					add: -1 1 0 2 10
					add: -1 1 0 2 11
					add: -1 1 0 2 12
					init: self
				)
			)
			(4
				(self setScript: leaveConvScr self)
			)
			(5
				(roomConv add: -1 1 0 2 13 10 10 init: self)
			)
			(6
				(self setScript: hideEgo self)
			)
			(7
				((inventory at: 27) owner: 730)
				((ScriptID 80 0) guardTimer: 301 setFlag: 709 512)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance leaveConvScr of Script
	(properties)
	
	(method (dispose)
		(= start (+ state 1))
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 80 5)
					init:
					setPri: 5
					setScale:
						Scaler
						(curRoom maxScaleSize?)
						(curRoom minScaleSize?)
						(curRoom maxScaleY?)
						(curRoom minScaleY?)
					posn: 200 87
					setMotion: MoveTo 93 105 self
				)
				((ScriptID 80 6)
					init:
					setPri: 5
					setScale:
						Scaler
						(curRoom maxScaleSize?)
						(curRoom minScaleSize?)
						(curRoom maxScaleY?)
						(curRoom minScaleY?)
					posn: 212 87
					setMotion: MoveTo 112 108 self
				)
			)
			(1 0)
			(2
				((ScriptID 80 5) setHeading: 180 self)
			)
			(3 (self dispose:))
			(4
				((ScriptID 80 5) setMotion: MoveTo 90 108 self)
			)
			(5
				((ScriptID 80 5) setHeading: 180 self)
			)
			(6 (= cycles 4))
			(7
				((ScriptID 80 5)
					view: 884
					loop: 0
					cel: 0
					setCycle: CycleTo 5 1 self
				)
			)
			(8
				(bird dispose:)
				((ScriptID 80 5) setCycle: EndLoop self)
			)
			(9 (self dispose:))
			(10
				(theMusic fadeTo: 700 -1)
				((ScriptID 80 5)
					view: 724
					loop: 2
					setCycle: StopWalk -1
					setMotion: MoveTo 52 111
				)
				((ScriptID 80 6) setMotion: MoveTo 52 120 self)
			)
			(11
				((ScriptID 80 5) dispose:)
				((ScriptID 80 6) dispose:)
				(= state -1)
				(self dispose:)
			)
		)
	)
)

(instance returningConvScr of Script
	(properties)
	
	(method (dispose param1)
		(if param1 (= start (+ state 1)) else (= start 0))
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				((ScriptID 80 5)
					init:
					setPri: 5
					setScale:
						Scaler
						(curRoom maxScaleSize?)
						(curRoom minScaleSize?)
						(curRoom maxScaleY?)
						(curRoom minScaleY?)
					posn: 37 113
					setMotion: MoveTo 93 105 self
				)
				((ScriptID 80 6)
					init:
					setPri: 5
					setScale:
						Scaler
						(curRoom maxScaleSize?)
						(curRoom minScaleSize?)
						(curRoom maxScaleY?)
						(curRoom minScaleY?)
					posn: 57 120
					setMotion: MoveTo 112 108 self
				)
			)
			(2 0)
			(3
				(if (OneOf ((inventory at: 26) owner?) 880 ego)
					(= state (+ state 4))
				)
				(self cue:)
			)
			(4
				(Face (ScriptID 80 5) (ScriptID 80 6))
				(Face (ScriptID 80 6) (ScriptID 80 5) self)
			)
			(5 (self dispose: 1))
			(6
				((ScriptID 80 5) setHeading: 45)
				((ScriptID 80 6) setHeading: 45 self)
			)
			(7 (self dispose: 1))
			(8
				(if (OneOf ((inventory at: 26) owner?) 880 ego)
					(hideEgo caller: 0)
					(= caller 0)
					(curRoom setScript: pictureDownGetEgo)
				else
					((ScriptID 80 5) setMotion: MoveTo 200 87 self)
					((ScriptID 80 6) setMotion: MoveTo 212 87)
				)
			)
			(9 (self dispose: 0))
		)
	)
)

(instance getNail of Script
	(properties)
	
	(method (dispose)
		(= start (= register 0))
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (= register (ego has: 26)) (ego put: 26 880))
				(theGame handsOff:)
				(ego
					view: 882
					loop: 3
					cel: 0
					x: 204
					y: 115
					normal: 0
					setScale: 0
					scaleX: 128
					scaleY: 128
					cycleSpeed: 8
					setCycle: CycleTo 3 1 self
				)
			)
			(1 (= cycles 5))
			(2
				(if register
					(messager say: 9 64 0 0 self)
					(nail init:)
				else
					(messager say: 7 5 0 0 self)
					(ego get: 26)
					(nail dispose:)
				)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(ego reset: 6 posn: 204 110)
				(if (not (Bset 88)) (theGame givePoints: 1))
				(if (or (== next waitForEgoToHide) (not next))
					(theGame handsOn:)
				)
				(self dispose:)
			)
		)
	)
)

(instance lookAtPicture of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 882
					loop: 0
					cel: 0
					x: 201
					y: 122
					setScale: 0
					scaleX: 128
					scaleY: 128
					normal: 0
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(1 (= seconds 2))
			(2 (messager say: 4 1 0 0 self))
			(3
				(theGame handsOn:)
				(theIconBar disable: 0 1 3 4 5)
				(portraitInset init: self curRoom)
			)
			(4
				(theGame handsOff:)
				(= register 0)
				(ego setCycle: BegLoop self)
			)
			(5
				(ego
					reset: 6
					posn: (portrait approachX?) (portrait approachY?)
				)
				(if (or (hideEgo caller?) (not next))
					(theGame handsOn:)
				)
				(self dispose:)
			)
		)
	)
)

(instance hideEgo of Script
	(properties)
	
	(method (dispose)
		(= register 0)
		(super dispose:)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(waitForEgoToHide register: 0)
				(++ state)
				(if
					(= temp0
						(cond 
							(
								(and
									(!= (curRoom script?) waitedToLongToEscape)
									((ScriptID 80 0) tstFlag: 709 2)
								)
								(-- state)
								15
							)
							((== ((inventory at: 27) owner?) 850) 16)
							(
								(and
									((ScriptID 80 0) tstFlag: 709 512)
									(not ((ScriptID 80 0) tstFlag: 710 1))
								)
								(-- state)
								14
							)
							(else 0)
						)
					)
					(messager say: 8 5 temp0 0 self)
				else
					(= cycles 1)
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
			(2
				(if (or register (= register (>= (ego x?) 126)))
					(= start 8)
					(ego setMotion: PolyPath 170 123 self)
				else
					(= start 9)
					(ego setMotion:)
				)
			)
			(3 (ego setHeading: 45 self))
			(4
				(= state 6)
				(ego
					view: 881
					setLoop: 4
					cel: 0
					setScale: 0
					scaleX: 128
					scaleY: 128
					x: 152
					y: 128
					normal: 0
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(5
				(ego
					view: 881
					setLoop: 0
					cel: 0
					x: 107
					y: 130
					normal: 0
					setScale: 0
					scaleX: 128
					scaleY: 128
					cycleSpeed: 8
				)
				(= cycles 3)
			)
			(6 (ego setCycle: EndLoop self))
			(7
				(if (== client curRoom)
					(= state (- start 1))
					(= cycles 10)
				else
					(self dispose:)
				)
			)
			(8
				(= state 12)
				(= register 1)
				(ego setCycle: BegLoop self)
			)
			(9
				(ego loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(10 (= seconds 2))
			(11 (ego setCycle: BegLoop self))
			(12
				(ego loop: 0 cel: 4 setCycle: BegLoop self)
			)
			(13
				(= start 0)
				(ego reset: 6)
				(if register
					(ego posn: 170 123)
				else
					(ego posn: 98 128)
				)
				(if (== client curRoom) (theGame handsOn:))
				(self dispose:)
			)
		)
	)
)

(instance guardsPatrol of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(= start 0)
	)
	
	(method (doit)
		(if (& local6 $4002) (curRoom newRoom: 850))
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(if (hideEgo client?)
					(-- state)
					(hideEgo caller: self)
				else
					(messager say: 1 0 1 0 self)
					((ScriptID 80 0) setFlag: 711 128)
				)
			)
			(2
				(theGame handsOn:)
				(hideEgo caller: self)
				(self setScript: waitForEgoToHide)
			)
			(3
				(theGame handsOff:)
				(if register (++ state))
				(self setScript: guardsWalk self)
			)
			(4 (messager say: 1 0 6 2 self))
			(5
				(if register
					(++ state)
					(= seconds 3)
				else
					(= seconds 2)
				)
			)
			(6 (messager say: 1 0 6 3 self))
			(7
				((ScriptID 80 0) clrFlag: 711 128)
				(if (OneOf ((inventory at: 26) owner?) 880 ego)
					(curRoom setScript: pictureDownGetEgo)
				else
					(self setScript: guardsWalk self)
				)
			)
			(8
				(if register
					(messager say: 1 0 8 2 self)
				else
					(messager say: 1 0 6 4 self)
				)
			)
			(9
				(self setScript: hideEgo self)
			)
			(10
				(theGame handsOn:)
				(= register 1)
				(= seconds 8)
			)
			(11
				(if (curRoom script?)
					(-- state)
					((curRoom script?) caller: self)
				else
					(messager say: 1 0 7 0 self)
					((ScriptID 80 0) setFlag: 711 128)
				)
			)
			(12
				(hideEgo caller: self)
				(self setScript: waitForEgoToHide)
			)
			(13
				(theGame handsOff:)
				(= state 2)
				(messager say: 1 0 8 1 self)
			)
		)
	)
)

(instance guardsWalk of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 temp2 temp3)
		(switch (= state newState)
			(0
				(if register
					(= temp0 200)
					(= temp1 87)
					(= temp2 212)
					(= temp3 87)
				else
					(= temp0 37)
					(= temp1 113)
					(= temp2 57)
					(= temp3 120)
				)
				((ScriptID 80 5) setMotion: MoveTo temp0 temp1 self)
				((ScriptID 80 6) setMotion: MoveTo temp2 temp3 self)
			)
			(1 0)
			(2
				(= register (not register))
				(self dispose:)
			)
		)
	)
)

(instance waitForEgoToHide of Script
	(properties)
	
	(method (dispose)
		(= seconds (= cycles 0))
		(super dispose:)
	)
	
	(method (changeState newState &tmp hideEgoCaller)
		(switch (= state newState)
			(0
				(if (> register 2) (self cue:) else (= seconds 7))
			)
			(1
				(theGame handsOff:)
				(if
					(and
						(<= register 2)
						(OneOf
							(curRoom script?)
							getNail
							takeDownPicture
							replacePicture
							lookAtPicture
						)
					)
					(-- state)
					((curRoom script?) caller: self)
				else
					(= hideEgoCaller (hideEgo caller?))
					(hideEgo caller: 0)
					(curRoom setScript: waitedTooLong 0 hideEgoCaller)
				)
			)
		)
	)
)

(instance waitedTooLong of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(self setScript: walkGuardsOutPartial self)
			)
			(1
				((ScriptID 80 6) setHeading: 180 self)
			)
			(2
				(switch register
					(watchGuardsComeBack
						(roomConv add: -1 1 0 21 1)
					)
					(guardsTakeBird
						(roomConv add: -1 1 0 20 1)
					)
					(else 
						(roomConv add: -1 1 0 17 1)
					)
				)
				(roomConv init: self)
			)
			(3
				(Face ego (ScriptID 80 5) self)
			)
			(4 (= cycles 2))
			(5
				(roomConv add: -1 1 0 17 2 add: -1 1 0 17 3 init: self)
			)
			(6
				(if (ego inRect: 57 117 124 169)
					(curRoom setScript: getEgo)
				else
					(curRoom spotEgo: (proc80_7))
				)
			)
		)
	)
)

(instance pictureDownGetEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(self
					setScript:
						walkGuardsOutPartial
						self
						(not ((ScriptID 80 0) tstFlag: 711 128))
				)
			)
			(2
				((ScriptID 80 6) setHeading: 180 self)
			)
			(3
				(messager say: 1 0 19 1 self)
			)
			(4
				(messager say: 1 0 19 2 self)
			)
			(5
				(self setScript: hideEgo self)
			)
			(6
				(messager say: 1 0 19 3 self)
			)
			(7
				(curRoom spotEgo: (proc80_7))
			)
		)
	)
)

(instance nail of View
	(properties
		x 226
		y 60
		noun 7
		approachX 204
		approachY 115
		view 880
		loop 1
		cel 1
		priority 6
		signal $6011
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
		((inventory at: 26) owner: 880)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5 (curRoom setScript: getNail))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bird of Prop
	(properties
		x 95
		y 110
		view 880
		loop 3
		signal $6000
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Forward)
	)
)

(instance portrait of View
	(properties
		heading 180
		noun 4
		sightAngle 40
		view 880
		loop 2
		priority 6
		signal $7011
	)
	
	(method (init)
		(if local0
			(self posn: 214 111)
		else
			(self posn: 223 87)
		)
		(super init: &rest)
		(self approachVerbs: 1 5)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(curRoom setScript: lookAtPicture)
			)
			(5
				(cond 
					((not local0) (curRoom setScript: takeDownPicture))
					((== ((inventory at: 26) owner?) 880) (curRoom setScript: replacePicture))
					(else (messager say: noun theVerb 13))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe event)
		(if (super onMe: event)
			(cond 
				(
					(and
						(== (theIconBar curIcon?) (theIconBar at: 2))
						(= approachX 198)
						(= approachY 116)
					)
				)
				((= approachX 204) (= approachY 110))
			)
		)
	)
)

(instance roomFeatures of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(= sightAngle 26505)
	)
	
	(method (doVerb theVerb)
		(switch noun
			(9
				(switch theVerb
					(64
						(curRoom setScript: getNail)
					)
					(else  (super doVerb: theVerb))
				)
			)
			(5
				(switch theVerb
					(5 (messager say: noun))
					(else 
						(if (== (approachCode doit: theVerb) -32768)
							(messager say: noun)
						else
							(super doVerb: theVerb)
						)
					)
				)
			)
			(6
				(if (== (approachCode doit: theVerb) -32768)
					(= theVerb 5)
				)
				(super doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (onMe event &tmp temp0)
		(asm
			pushi    3
			pushi    4
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			callk    OnControl,  6
			sat      temp0
			ldi      0
			aTop     _approachVerbs
			aTop     approachY
			aTop     approachX
			pushi    5
			pushi    71
			pushi    39
			pushi    251
			pushi    144
			lsp      event
			calle    InRect,  10
			not     
			bnt      code_1ded
			ldi      6
			aTop     noun
			bt       code_1e60
code_1ded:
			pushi    4
			lat      temp0
			and     
			bnt      code_1e44
			ldi      9
			aTop     noun
			bnt      code_1e44
			pushi    #curIcon
			pushi    0
			lag      theIconBar
			send     4
			push    
			pushi    #at
			pushi    1
			pushi    4
			lag      theIconBar
			send     6
			eq?     
			bnt      code_1e3f
			pushi    #curInvIcon
			pushi    0
			lag      theIconBar
			send     4
			push    
			pushi    #at
			pushi    1
			pushi    26
			lag      inventory
			send     6
			eq?     
			bnt      code_1e3f
			ldi      204
			aTop     approachX
			bnt      code_1e3f
			ldi      110
			aTop     approachY
			bnt      code_1e3f
			ldi      32768
			aTop     _approachVerbs
			bt       code_1e60
code_1e3f:
			ldi      1
			bt       code_1e60
code_1e44:
			pushi    8
			lat      temp0
			and     
			bnt      code_1e53
			ldi      10
			aTop     noun
			bt       code_1e60
code_1e53:
			pushi    16480
			lat      temp0
			and     
			bnt      code_1e60
			ldi      5
			aTop     noun
code_1e60:
			ret     
		)
	)
)

(instance pillar of Feature
	(properties
		x 126
		y 123
		noun 8
		sightAngle 40
		onMeCheck $0002
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(theGame handsOff:)
			(if (hideEgo caller?)
				((hideEgo caller?) setScript: hideEgo)
			else
				(curRoom setScript: hideEgo 0)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance portraitInset of Inset
	(properties
		view 880
		disposeNotOnMe 1
		noun 11
	)
	
	(method (init)
		(= x (- 160 (/ (CelWide view loop cel) 2)))
		(= y (- 90 (/ (CelHigh view loop cel) 2)))
		(super init: &rest)
	)
)

(instance roomConv of Conversation
	(properties)
)

(instance getEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic stop:)
				(theGlobalSound number: 710 loop: -1 play:)
				((ScriptID 80 5) setMotion: MoveTo 78 109 self)
				((ScriptID 80 6) setLoop: 2 setMotion: MoveTo 100 120)
			)
			(1
				((ScriptID 80 5)
					setLoop: 2
					setMotion: MoveTo 76 120 self
				)
			)
			(2 (= ticks 30))
			(3
				(theGlobalSound fade:)
				(rgCastle
					rFlag1: (| (rgCastle rFlag1?) $2000)
					dungeonEntered: 3
				)
				(curRoom newRoom: 820)
			)
		)
	)
)

(instance watchGuardsComeBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 150)
				((ScriptID 1012 32) talkWidth: 100 x: 160 y: 10)
				((ScriptID 80 0) clrFlag: 710 1)
				(= cycles 3)
			)
			(1
				(self setScript: returningConvScr self)
			)
			(2
				(roomConv
					add: -1 1 0 3 2
					add: -1 1 0 3 3
					add: -1 1 0 3 4
					add: -1 1 0 3 5
					add: -1 1 0 3 6
					add: -1 1 0 3 7 -1 10
					init: self
				)
			)
			(3
				(self setScript: returningConvScr self)
			)
			(4
				(roomConv
					add: -1 1 0 3 8
					add: -1 1 0 3 9 -1 10
					add: -1 1 0 3 10
					add: -1 1 0 3 11
					add: -1 1 0 3 12 -1 10
					add: -1 1 0 3 13
					add: -1 1 0 3 14 -1 10
					add: -1 1 0 3 15
					add: -1 1 0 3 16 -1 10
					add: -1 1 0 3 17
					add: -1 1 0 3 18
					add: -1 1 0 3 19
					init: self
				)
			)
			(5
				(self setScript: returningConvScr self)
			)
			(6
				(roomConv add: -1 1 0 3 20 -1 10 init: self)
			)
			(7
				(self setScript: hideEgo self)
			)
			(8
				((ScriptID 80 0) weddingRemind: 125 clrFlag: 709 512)
				((ScriptID 80 0) clrFlag: 710 1 setFlag: 709 2)
				(= cycles 3)
			)
			(9
				(theGame handsOn:)
				(Bclr 150)
				(self dispose:)
			)
		)
	)
)
