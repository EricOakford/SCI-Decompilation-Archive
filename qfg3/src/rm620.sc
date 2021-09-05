;;; Sierra Script 1.0 - (do not remove this comment)
(script# 620)
(include game.sh)
(use Main)
(use TellerIcon)
(use Feature)
(use ForCount)
(use LoadMany)
(use Reverse)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm620 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
	newView
	local8
	local9 = [0 -8 -4 -9 -2 -3 -1 -10 -11 -12 -14 999]
	[local21 3]
	local24 = [0 -4 -3 -6 -7 -5 -13 999]
	[local32 2]
	local34 = [0 -1 -2 -3 -4 -5 -13 999]
	[local42 2]
	local44 = [0 -10 -4 -3 -6 7 -13 -5 999]
	[local53 5]
	local58 = [0 -5 999]
	[local61 2]
)
(procedure (localproc_1b51)
	(leaderTell init: leader @local34 @local42)
	(egoTell init: ego @local9 @local21)
	(shamanTell init: shaman @local44 @local53)
	(johariTell init: (ScriptID 36 1) @local24 @local32)
	((= newView (View new:))
		view: 620
		loop: 2
		cel: 0
		x: 43
		y: 111
		signal: ignrAct
		noun: 4
		actions: leopManTell
		init:
	)
	(leopManTell init: newView @local58 @local61)
	((View new:)
		view: 620
		loop: 2
		cel: 0
		x: 108
		y: 128
		signal: ignrAct
		noun: 4
		actions: leopManTell
		init:
	)
	((View new:)
		view: 620
		loop: 2
		cel: 1
		x: 205
		y: 127
		signal: ignrAct
		noun: 4
		actions: leopManTell
		init:
	)
	((View new:)
		view: 620
		loop: 2
		cel: 3
		x: 255
		y: 115
		signal: ignrAct
		noun: 4
		actions: leopManTell
		init:
	)
	((View new:)
		view: 620
		loop: 2
		cel: 3
		x: 210
		y: 103
		signal: ignrAct
		noun: 4
		actions: leopManTell
		init:
	)
	(fire init: setCycle: Forward)
	((ScriptID 36 1)
		view: 620
		loop: 4
		x: 170
		y: 98
		cel: 1
		init:
		noun: 2
		signal: ignrAct
	)
	(ego
		x: 188
		y: 107
		loop: 1
		cel: 0
		setScale: 140
		noun: 5
		init:
		normalize:
	)
	(throne init:)
	(platform init:)
)

(procedure (localproc_1d42)
	(dancer1 setCel: 0 setCycle: EndLoop)
	(dancer2 setCel: 0 setCycle: EndLoop)
	(dancer3 setCel: 0 setCycle: EndLoop)
	(dancer4 setCel: 0 setCycle: EndLoop)
	(dancer7 setCel: 0 setCycle: EndLoop)
	(dancer8 setCel: 0 setCycle: EndLoop)
)

(procedure (localproc_1da9)
	(egoAndJohari init:)
	(drummer1 init:)
	(drummer2 init:)
	(drummer3 init:)
	(dancer1 setCycle: Forward init:)
	(dancer2 setCycle: Forward init:)
	(dancer3 setCycle: Forward init:)
	(dancer4 setCycle: Forward init:)
	(dancer7 setCycle: Forward init:)
	(dancer8 setCycle: Forward init:)
)

(class Wcycler of Cycle
	(properties
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		head 0
		tail 10
		flash 0
	)
	
	(method (init param1 param2 param3)
		(super init: param1)
		(self head: param2)
		(if (> param3 (client lastCel:))
			(self tail: (client lastCel:))
		else
			(self tail: param3)
		)
	)
	
	(method (doit &tmp wcyclerNextCel)
		(if (not flash)
			(client hide:)
			(= flash 1)
		else
			(= flash 0)
			(if
			(> (= wcyclerNextCel (self nextCel:)) (self tail?))
				(self cycleDone:)
			else
				(client show:)
				(client cel: wcyclerNextCel)
			)
		)
	)
	
	(method (cycleDone)
		(client cel: head)
	)
)

(instance rm620 of Room
	(properties
		noun 11
		picture 620
		vanishingY -25
	)
	
	(method (init)
		(= [local42 0] @local34)
		(= [local21 0] @local9)
		(= [local32 0] @local24)
		(= [local53 0] @local44)
		(= [local61 0] @local58)
		(cond 
			((== prevRoomNum 420) (= local0 1))
			((== prevRoomNum 650) (= local0 4))
			((not (Btst 37)) (= local0 0))
			((ego has: 46) (= local0 2))
			(else (= local0 3))
		)
		(switch local0
			(0
				(LoadMany 128 620 621 622)
				(localproc_1da9)
				(Bset 37)
				(super init: &rest)
				(curRoom setScript: changeDance)
				(cSound number: 620 setLoop: 1 play: self)
			)
			(1
				(if (not (Btst 165)) (shaman init: noun: 3 stopUpd:))
				(leader init: noun: 1)
				(if (!= egoGait 0) (ego changeGait: 0))
				(cSound number: 480 setLoop: 1 play:)
				(localproc_1b51)
				(super init: &rest)
				(curRoom setScript: backTarna)
			)
			(2
				(LoadMany 128 605 606 625 626)
				(shaman init: noun: 3 stopUpd:)
				(leader init: noun: 1)
				(if (!= egoGait 0) (ego changeGait: 0))
				(cSound number: 480 setLoop: -1 play:)
				(localproc_1b51)
				(super init: &rest)
				(curRoom setScript: enterWithDrum)
			)
			(3
				(LoadMany 128 605 606 625 626)
				(leader init: x: 83 y: 94 noun: 1)
				(effect init: show:)
				(localproc_1b51)
				(cSound number: 480 setLoop: 1 play:)
				(super init: &rest)
				(curRoom setScript: muEnter)
			)
			(4
				(LoadMany 128 605 606 625 626)
				(leader init: x: 83 y: 94 signal: ignrAct noun: 1)
				(localproc_1b51)
				(cSound number: 480 setLoop: 1 play:)
				(super init: &rest)
				(curRoom setScript: eventFour)
			)
		)
	)
	
	(method (dispose)
		(UnLoad 128 605)
		(UnLoad 128 606)
		(UnLoad 128 625)
		(UnLoad 128 626)
		(UnLoad 128 620)
		(UnLoad 128 621)
		(UnLoad 128 622)
		(LoadMany 0 36 44 43 956)
		(super dispose: &rest)
	)
	
	(method (cue)
		(switch (cSound prevSignal?)
			(20
				(drummer1 setCycle: EndLoop)
				(drummer2 setCycle: EndLoop)
				(drummer3 setCycle: EndLoop)
				(= local8 1)
				(localproc_1d42)
			)
			(30
				(= local8 0)
				(drummer1 setCycle: Forward)
				(drummer2 setCycle: Forward)
				(drummer3 setCycle: Forward)
			)
			(-1
				(changeDance cue:)
				(cSound number: 622 play:)
			)
			(10
				(if local8 (localproc_1d42))
			)
		)
	)
)

(instance backTarna of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 5))
			(1
				(messager say: 2 6 49 0 self)
			)
			(2
				(ego
					normalize:
					setMotion: MoveTo (+ (leader x?) 24) 107 self
					solvePuzzle: 298 20
				)
			)
			(3 (Face ego leader self))
			(4 (= cycles 20))
			(5
				(ego
					setCycle: Reverse
					setLoop: 1
					setMotion: MoveTo 138 107 self
				)
			)
			(6
				(leader x: 99 y: 99 cel: 0 setLoop: 0 setCycle: EndLoop self)
				(ego setCycle: Walk setLoop: -1 normalize:)
			)
			(7
				(leader cel: 0 setLoop: 1 setCycle: EndLoop self)
			)
			(8
				(leader loop: 2 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(9 (messager say: 1 6 4 0 self))
			(10 (= seconds 2))
			(11
				(leader cycleSpeed: 12 setCycle: CycleTo 7 1 self)
			)
			(12
				(leader setCycle: CycleTo 11 1 self)
			)
			(13
				(leader setLoop: 3 setCycle: EndLoop self)
			)
			(14
				(messager say: 1 5 2 0 self)
			)
			(15
				(ego drop: 46)
				(= local3 1)
				(ego addHonor: 50)
				(messager say: 7 6 47 0 self)
			)
			(16
				(cast eachElementDo: #hide)
				(DrawPic 0 PIXELDISSOLVE)
				(= seconds 1)
			)
			(17 (= seconds 1))
			(18
				(messager say: 7 6 48 0 self)
			)
			(19 (curRoom newRoom: 340))
		)
	)
)

(instance eventFour of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fire setPri: (= cycles 10))
			)
			(1
				(messager say: 1 2 14 0 self)
			)
			(2
				(HandsOn 5 1 3 6)
				(self dispose:)
			)
		)
	)
)

(instance egoBows of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 5))
			(1
				(if (== local0 3)
					(self changeState: 4)
				else
					(ego setMotion: MoveTo 139 105 self)
				)
			)
			(2 (Face ego leader self))
			(3 (= cycles 5))
			(4
				(ego
					view: 32
					setLoop: 1
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(5
				(switch register
					(1
						(messager say: 5 6 19 0 self)
					)
					(2
						(messager say: 5 6 16 0 self)
					)
					(3
						(messager say: 5 6 31 0 self)
					)
					(4
						(messager say: 5 6 44 0 self)
					)
				)
			)
			(6
				(ego setLoop: -1 cycleSpeed: 6 normalize: addHonor: 5)
				(HandsOn 5 1 3 6)
				(self dispose:)
			)
		)
	)
)

(instance changeDance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local8 1)
				(= seconds 2)
			)
			(1 (messager say: 2 2 10 0))
			(2
				(dancer1 loop: 1 cel: 0 setCycle: CycleTo 3 1)
				(dancer4 loop: 3 cel: 0 setCycle: CycleTo 2 1)
				(dancer7 loop: 1 cel: 0 setCycle: CycleTo 3 1)
				(dancer8 loop: 1 cel: 0 setCycle: CycleTo 3 1 self)
				(dancer2 loop: 3 cel: 0 setCycle: CycleTo 2 1 self)
				(dancer3 loop: 3 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(3
				(dancer1 setCycle: Wcycler 3 4)
				(dancer4 setCycle: Wcycler 2 3)
				(dancer7 setCycle: Wcycler 3 4)
				(dancer8 setCycle: Wcycler 3 4)
				(dancer2 setCycle: Wcycler 2 3)
				(dancer3 setCycle: Wcycler 2 3)
				(= seconds 4)
			)
			(4
				(Palette PALIntensity 0 255 1000)
				(Palette PALIntensity 0 255 100)
				(dancer1 show: setCycle: EndLoop)
				(dancer4 show: setCycle: EndLoop)
				(dancer7 show: setCycle: EndLoop)
				(dancer8 show: setCycle: EndLoop)
				(dancer2 show: setCycle: EndLoop)
				(dancer3 show: setCycle: EndLoop)
				(= seconds 2)
			)
			(5
				(messager say: 2 2 15 0 self)
			)
			(6
				(if (== heroType 2)
					(curRoom newRoom: 630)
				else
					(curRoom newRoom: 600)
				)
			)
		)
	)
)

(instance muEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(fire setPri: 10)
				(= cycles 10)
			)
			(1
				(messager say: 1 2 27 0 self)
			)
			(2
				(messager say: 2 2 28 0 self)
			)
			(3
				(HandsOn 5 1 3 6)
				(self dispose:)
			)
		)
	)
)

(instance shamanEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(effect show:)
				(= cycles 5)
			)
			(1
				(messager say: 5 5 10 0 self)
			)
			(2
				(globalSound number: 831 play: setLoop: -1)
				(effect cycleSpeed: 0 setCycle: ForwardCounter 3 self)
			)
			(3
				(shaman
					view: 625
					loop: 0
					cel: 0
					x: 138
					y: 103
					priority: (- (effect priority?) 1)
					noun: 3
					init:
				)
				(shamanTell init: shaman @local44 @local53)
				(effect setCycle: EndLoop self)
			)
			(4
				(effect dispose:)
				(shaman setCycle: EndLoop self)
			)
			(5
				(shaman
					x: 145
					y: 101
					cel: 0
					setLoop: 1
					setCycle: CycleTo 5 1 self
				)
			)
			(6
				(messager say: 3 6 32 0 self)
			)
			(7
				(shaman setCycle: CycleTo 9 1 self)
			)
			(8
				(messager say: 2 2 33 0 self)
			)
			(9
				(= local1 1)
				(= local2 0)
				(HandsOn 5 1 3 6)
				(self dispose:)
			)
		)
	)
)

(instance giveSpear of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego normalize:)
				(switch register
					(1
						(ego addHonor: 50)
						(messager say: 5 6 41 0 self)
					)
					(2
						(messager say: 5 5 43 0 self)
					)
					(3
						(messager say: 5 6 22 0 self)
					)
					(5
						(messager say: 5 6 23 0 self)
					)
					(else 
						(messager say: 5 5 1 0 self)
					)
				)
			)
			(1
				(if local3
					(self cue:)
				else
					(self setScript: chiefStand self)
				)
			)
			(2
				(leader loop: 4 setCycle: Forward)
				(globalSound number: 12 setLoop: 1 play: 127 self)
			)
			(3
				(leader setCycle: 0)
				(spear init:)
				(= cycles 10)
			)
			(4
				(if (== local0 4)
					(ego setMotion: MoveTo 135 107 self)
				else
					(self cue:)
				)
			)
			(5
				(ego
					view: 31
					cycleSpeed: 12
					setLoop: 1
					setCycle: BegLoop self
				)
			)
			(6
				(spear dispose:)
				(ego setLoop: -1 cycleSpeed: 6 get: 45 normalize:)
				(= cycles 2)
			)
			(7
				(leader setLoop: 1 setCycle: CycleTo 0 -1 self)
			)
			(8
				(ego
					view: 13
					loop: 0
					cel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(9 (= seconds 5))
			(10
				(cast eachElementDo: #hide)
				(curRoom drawPic: 0)
				(= seconds 2)
			)
			(11
				(messager say: 7 2 17 0 self)
				(if (PalVary PALVARYINFO)
					(Bclr 81)
					(PalVary PALVARYREVERSE 1)
				)
			)
			(12
				(ego cycleSpeed: 6 normalize:)
				((ScriptID 7 4) init: 10 0)
				(= cycles 5)
			)
			(13 (curRoom newRoom: 420))
		)
	)
)

(instance chiefStand of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(leader x: 99 y: 99 loop: 1 setCycle: EndLoop self)
			)
			(1 (= cycles 10))
			(2 (self dispose:))
		)
	)
)

(instance enterWithDrum of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(fire setPri: 10)
				(= cycles 10)
			)
			(1
				(messager say: 1 2 18 0 self)
			)
			(2
				(messager say: 3 6 18 0 self)
			)
			(3
				((ScriptID 36 1)
					view: 620
					cel: 2
					setLoop: 4
					setScale: 0
					setCycle: EndLoop self
				)
			)
			(4
				(messager say: 2 2 20 0 self)
			)
			(5
				((ScriptID 36 1) setCycle: CycleTo 0 -1 self)
			)
			(6
				(HandsOn 5 1 3 6)
				(self dispose:)
			)
		)
	)
)

(instance egoGiveDrum of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					normalize:
					setMotion: MoveTo (+ (leader x?) 24) 107 self
					solvePuzzle: 298 20
				)
			)
			(1 (Face ego leader self))
			(2 (= cycles 20))
			(3
				(ego
					setCycle: Reverse
					setLoop: 1
					setMotion: MoveTo 138 107 self
				)
			)
			(4
				(leader x: 99 y: 99 cel: 0 setLoop: 0 setCycle: EndLoop self)
				(ego setCycle: Walk setLoop: -1 normalize:)
			)
			(5
				(leader cel: 0 setLoop: 1 setCycle: EndLoop self)
			)
			(6
				(leader loop: 2 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(7 (messager say: 1 6 4 0 self))
			(8 (= seconds 2))
			(9
				(leader cycleSpeed: 12 setCycle: CycleTo 7 1 self)
			)
			(10
				(leader setCycle: CycleTo 11 1 self)
			)
			(11
				(leader setLoop: 3 setCycle: EndLoop self)
			)
			(12
				(= local6 1)
				(if (and (ego has: 45) (== heroType 2))
					(self cue:)
				else
					(messager say: 2 6 4 0 self)
				)
			)
			(13
				(leader cycleSpeed: 6)
				(Bset 13)
				(= local3 1)
				(ego addHonor: 50)
				(if (and (ego has: 45) (== heroType 2))
					(self cue:)
				else
					(HandsOn 5 1 3 6)
					(ego drop: 46)
					(self dispose:)
				)
			)
			(14
				(ego drop: 46)
				(cast eachElementDo: #hide)
				(curRoom drawPic: 0)
				(= seconds 2)
			)
			(15
				(messager say: 7 2 35 0 self)
			)
			(16
				(ego cycleSpeed: 6 normalize:)
				(curRoom newRoom: 420)
			)
		)
	)
)

(instance accept of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 5 6 11 0 self)
			)
			(1
				(leader x: 98 y: 98 cel: 0 setLoop: 1 setCycle: EndLoop self)
			)
			(2
				(messager say: 1 6 13 0 self)
			)
			(3 (curRoom newRoom: 650))
		)
	)
)

(instance fire of Prop
	(properties
		x 149
		y 101
		noun 8
		view 620
		signal $4000
	)
)

(instance spear of View
	(properties
		x 135
		y 93
		view 620
		loop 1
		signal $4000
	)
)

(instance dancer1 of Prop
	(properties
		x 207
		y 104
		view 622
		loop 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: shamanEnters)
			)
			(2
				(leopManTell client: dancer1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance dancer2 of Prop
	(properties
		x 107
		y 130
		view 622
		loop 2
	)
)

(instance dancer3 of Prop
	(properties
		x 203
		y 130
		view 622
		loop 2
	)
)

(instance dancer4 of Prop
	(properties
		x 154
		y 136
		view 622
		loop 2
	)
)

(instance dancer7 of Prop
	(properties
		x 105
		y 101
		view 622
		loop 5
	)
)

(instance dancer8 of Prop
	(properties
		x 155
		y 101
		view 622
	)
)

(instance egoAndJohari of View
	(properties
		x 205
		y 139
		view 620
		loop 3
	)
)

(instance leader of Prop
	(properties
		x 83
		y 94
		view 605
	)
)

(instance shaman of Prop
	(properties
		x 74
		y 103
		view 625
	)
)

(instance drummer1 of Prop
	(properties
		x 67
		y 125
		view 621
		cel 1
	)
)

(instance drummer2 of Prop
	(properties
		x 52
		y 122
		view 621
		cel 2
	)
)

(instance drummer3 of Prop
	(properties
		x 46
		y 118
		view 621
		cel 3
	)
)

(instance effect of Prop
	(properties
		x 119
		y 89
		view 625
		loop 2
		signal $4000
	)
)

(instance egoTell of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				-8
				(if (not local2) (not local3) else 0)
				-4
				(== local0 2)
				-10
				(== local0 3)
				-9
				(== local1 0)
				-11
				(if (== local0 3) (== local1 1) else 0)
				-12
				(if (== local0 3) (== local1 1) else 0)
				-14
				(== local0 4)
				-3
				(== local0 4)
				-2
				(if (== local1 0) (not local4) else 0)
				-1
				(if (== local0 2) (not local5) else 0)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-9
					(cond 
						((== local0 2)
							(if local6
								(messager say: 5 6 36)
							else
								(messager say: 5 5 9)
							)
						)
						((== local0 3) (messager say: 5 5 40))
						((== local0 4) (messager say: 5 5 45))
						(else (messager say: 5 5 9))
					)
					(return 0)
				)
				(-11
					(ego addHonor: 20)
					(curRoom setScript: accept)
					(return 0)
				)
				(-12
					(ego addHonor: -20)
					(messager say: 5 6 12)
					(return 0)
				)
				(-4
					(cond 
						((ego has: 46)
							(messager say: 5 5 4)
							(curRoom setScript: egoGiveDrum)
							(return 0)
						)
						((== local3 1) (curRoom setScript: giveSpear 0 3) (return 0))
					)
					(return query)
				)
				(-8
					(cond 
						((== local0 4) (curRoom setScript: egoBows 0 4))
						(local1 (curRoom setScript: egoBows 0 3))
						(else (curRoom setScript: egoBows 0 1))
					)
					(= local2 1)
					(return 0)
				)
				(-1
					(cond 
						((ego has: 46)
							(messager say: 5 6 46)
							(curRoom setScript: egoGiveDrum)
							(return 0)
						)
						((== local3 1)
							(ego addHonor: 30)
							(curRoom setScript: giveSpear 0 5)
							(= local5 1)
							(return 0)
						)
					)
					(return query)
				)
				(-2
					(cond 
						((== local0 4) (curRoom setScript: giveSpear 0 1) (return 0))
						((== local3 1)
							(curRoom setScript: giveSpear 0 1)
							(ego addHonor: 30)
							(= local4 1)
							(return 0)
						)
						((== local0 2)
							(messager say: 5 5 2)
							(curRoom setScript: egoGiveDrum)
							(return 0)
						)
						(else (messager say: 5 6 30) (return 0))
					)
				)
				(-3
					(curRoom setScript: giveSpear 0 2)
					(return 0)
				)
				(-10
					(if (not (== local1 1))
						(curRoom setScript: shamanEnters)
					else
						(curRoom setScript: accept)
					)
					(return 0)
				)
				(else  (return query))
			)
		)
	)
)

(instance leaderTell of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				-13
				(if (== local0 3) (== local1 1) else 0)
				-1
				(if (== local0 4) else (== local0 2))
				-2
				(if (== local0 4) else (== local0 2))
				-3
				(== local0 4)
				-4
				(== local0 2)
				-5
				(cond 
					((== local0 2))
					((== local0 3) (not local1))
				)
		)
	)
	
	(method (doChild param1)
		(return
			(switch param1
				(-1
					(if local6
						(messager say: 1 5 1)
					else
						(messager say: 1 5 42)
					)
					(return 0)
				)
				(-2
					(cond 
						((== local0 3) (messager say: 1 5 39))
						((== local0 4) (messager say: 1 5 41))
						(local6 (messager say: 1 5 2))
						(else (messager say: 1 5 41))
					)
					(return 0)
				)
				(-5
					(if (== local0 2)
						(messager say: 1 5 26)
					else
						(messager say: 2 5 29)
					)
					(return 0)
				)
				(-4
					(messager say: 1 5 4)
					(return 0)
				)
				(-3
					(if (== local3 1)
						(curRoom setScript: giveSpear 0 2)
						(return 0)
					)
					(return query)
				)
				(else  (return query))
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(57
				(if (== local0 2)
					(curRoom setScript: egoGiveDrum)
				else
					(super doVerb: theVerb)
				)
			)
			(20 (curRoom doVerb: theVerb))
			(33 (curRoom doVerb: theVerb))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance shamanTell of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				-4
				(== local0 2)
				-3
				(== local0 2)
				-6
				(== local0 2)
				-5
				(== local0 2)
				-10
				(== local0 3)
				-7
				(== local0 3)
				-13
				(== local0 3)
		)
	)
	
	(method (doChild)
		(return
			(if (or (== local0 2) (== local0 3))
				(if (== local3 1)
					(messager say: 0 5 21)
				else
					(messager say: 0 5 34)
				)
				(return 0)
			else
				(return query)
			)
		)
	)
)

(instance leopManTell of Teller
	(properties)
	
	(method (doChild)
		(return
			(cond 
				((== local0 2)
					(if (== local3 1)
						(messager say: 0 5 21)
					else
						(messager say: 0 5 34)
					)
					(return 0)
				)
				((== local0 3)
					(if (not local1)
						(messager say: 0 5 37)
					else
						(messager say: 0 5 38)
					)
					(return 0)
				)
				(else (messager say: 0 5 21) (return 0))
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(20 (curRoom doVerb: theVerb))
			(33 (curRoom doVerb: theVerb))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance johariTell of Teller
	(properties)
	
	(method (showDialog)
		(super
			showDialog:
				-4
				(== local0 2)
				-13
				local1
				-3
				(if (== local0 2) else (== local0 4))
				-6
				(== local0 2)
				-7
				(cond 
					((== local0 2))
					((== local0 3) local1)
				)
				-5
				(cond 
					((== local0 2))
					((== local0 3) (not local1))
				)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-4
					(if (== local0 2)
						(if (== local3 1)
							(messager say: 2 5 3)
						else
							(messager say: 2 5 25)
						)
						(return 0)
					else
						(return query)
					)
				)
				(-3
					(if (== local0 2)
						(if (== local3 1)
							(messager say: 2 5 3)
						else
							(messager say: 2 5 25)
						)
						(return 0)
					else
						(return query)
					)
				)
				(-6
					(if (== local0 2)
						(if (== local3 1)
							(messager say: 2 5 3)
						else
							(messager say: 2 5 25)
						)
						(return 0)
					else
						(return query)
					)
				)
				(-7
					(cond 
						((== local0 2)
							(if (== local3 1)
								(messager say: 2 5 3)
							else
								(messager say: 2 5 25)
							)
							(return 0)
						)
						((== local0 3)
							(if (not local1)
								(messager say: 2 5 29)
								(return 0)
							else
								(return query)
							)
						)
						(else (return query))
					)
				)
				(-5
					(if (== local0 2)
						(if (== local3 1)
							(messager say: 2 5 3)
						else
							(messager say: 2 5 25)
						)
						(return 0)
					else
						(return query)
					)
				)
				(else  (return query))
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(20 (curRoom doVerb: theVerb))
			(33 (curRoom doVerb: theVerb))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance throne of Feature
	(properties
		x 90
		y 66
		noun 9
		nsTop 40
		nsLeft 67
		nsBottom 92
		nsRight 114
	)
)

(instance platform of Feature
	(properties
		x 157
		y 61
		z 50
		noun 10
		nsTop 96
		nsLeft 53
		nsBottom 127
		nsRight 261
	)
)
