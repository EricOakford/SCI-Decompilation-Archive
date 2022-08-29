;;; Sierra Script 1.0 - (do not remove this comment)
(script# 240)
(include game.sh)
(use Main)
(use Procs)
(use GKEgo)
(use RoomTeller)
(use Print)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use ForCount)
(use LoadMany)
(use StopWalk)
(use Grooper)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm240 0
)

(local
	moselyTalkCount =  1
	moselyInRoom
	sawFrankyInWindow
	local3
	local4 =  1
	local5 =  1
	gabrielSitting =  1
	local7
)
;EO: Gabriel's studio is inaccessible in the demo, and the pic and message files are
;absent, so there's not much that can be filled in.

(instance rm240 of Room
	(properties
		noun 26
		picture 240
		style FADEOUT
	)
	
	(method (init)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						70
						66
						101
						90
						103
						101
						4
						132
						4
						144
						118
						144
						130
						154
						197
						154
						205
						143
						292
						144
						299
						136
						269
						128
						252
						136
						227
						133
						213
						120
						201
						134
						179
						138
						145
						134
						139
						122
						123
						129
						107
						124
						102
						113
						110
						100
						148
						112
						168
						104
						132
						95
						142
						87
						107
						85
						81
						63
					yourself:
				)
		)
		(LoadMany 130 920 51)
		(Load RES_MESSAGE 240)
		(features
			add:
				bookshelf
				microwave
				fridge
				bullBoard
				desk
				waste
				leftFileCab
				leftPlant
				rightPlant
				winder
				leftBinder
				rightBinder
				birthday
				deskLamp
				trumPost
				speaker
				rightFileCab
				falconPoster
				gabsChair
			eachElementDo: #init
		)
		(theDoor init:)
		(if
			(not
				(if (or (== currentDay 3) (Btst 20) (Btst 34))
				else
					(Btst 19)
				)
			)
			(frankyInWindow init: setScript: frankyAtFiles)
		)
		(if (< currentDay 6)
			(mosely init:)
			(photoTeller init: mosely)
		else
			(mosChair init:)
		)
		(Load RES_VIEW 2401)
		(walkHandler add: self)
		(cond 
			((== prevRoomNum 50)
				(Load RES_VIEW 245)
				(ego view: 2401 loop: 0 cel: 6 posn: 133 124 init:)
				(frankyAtFiles setScript: 0 dispose:)
				(cond 
					((Btst 17)
						(Bclr 17)
						(Bset 18)
						(curRoom setScript: mosLeaveRoom 0 63)
					)
					((Btst 18) (Bclr 18) (curRoom setScript: mosLeaveRoom 0 64))
					((Btst 19) (curRoom setScript: photoFirst))
					((and (== currentDay 2) (> global179 74)) (mosely setScript: geezItsHot))
				)
			)
			((Btst 20) (Bclr 20) (curRoom setScript: photoReplay))
			((and (== currentDay 3) (not (Btst 21)))
				(curRoom setScript: crashInterview)
				(ego setScript: egoEnters)
			)
			((and (== currentDay 5) (Btst 106)) (curRoom setScript: giveTheBadgeBack))
			(else
				(curRoom setScript: egoEnters)
				(if (and (== currentDay 2) (> global179 74))
					(mosely setScript: geezItsHot)
				)
			)
		)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(LoadMany 0 920 51 939)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(if gabrielSitting
					(ego setScript: standGabUp)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance geezItsHot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOn:)
				(= seconds 10)
			)
			(1
				(theGame handsOff:)
				(messager say: 27 0 73 local5 self)
			)
			(2
				(if (== local5 3)
					(mosely view: 247 loop: 0 cel: 0 setCycle: EndLoop self)
				else
					(theGame handsOn:)
					(if (< local5 3) (self init:) else (self dispose:))
				)
				(++ local5)
			)
			(3
				(mosely loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(4
				(mosely loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(5
				(Bset 98)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance standGabUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 2401 loop: 0 cel: 6 setCycle: BegLoop self)
			)
			(1
				(theGame handsOn:)
				(= gabrielSitting 0)
				(ego normalize: ignoreActors: 1 ignoreControl: -32768)
				(self dispose:)
			)
		)
	)
)

(instance sitGabDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 133 124 self)
			)
			(1
				(ego view: 2401 setLoop: 0 cel: 2 setCycle: EndLoop self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 900
					loop: 2
					posn: 103 88
					setCycle: StopWalk -1
					init:
				)
				(if (and (== currentDay 3) (not (Btst 21)))
					(self dispose:)
				else
					(ego setMotion: PolyPath 133 124 self)
				)
			)
			(1
				(ego view: 2401 setLoop: 0 cel: 2 setCycle: EndLoop self)
			)
			(2
				(cond 
					((and (== currentDay 2) (> global179 74)) (mosely setScript: geezItsHot))
					((and (== currentDay 4) (not (Btst 22)))
						(Bset 22)
						(messager say: 27 0 66 0)
						(theGame handsOn:)
						(self dispose:)
					)
					(else (theGame handsOn:) (self dispose:))
				)
			)
		)
	)
)

(instance photoFirst of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local4 0)
				(Bset 19)
				(theGame handsOff:)
				(mosely view: 243 loop: 3 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(1
				(mosChair init:)
				(mosely
					view: 245
					loop: egoLooper
					posn: 217 126
					setCycle: StopWalk -1
					setMotion: PolyPath 104 90 self
				)
			)
			(2
				(mosely
					posn: 108 92
					view: 2421
					loop: 0
					cel: 0
					setCycle: CycleTo 8 1 self
				)
			)
			(3
				(messager say: 27 0 68 1 self)
			)
			(4
				(mosely
					posn: 108 92
					view: 2421
					loop: 0
					cel: 9
					setCycle: EndLoop self
				)
			)
			(5
				(mosely
					view: 245
					posn: 104 90
					setCycle: StopWalk -1
					setMotion: PolyPath 223 128 self
				)
			)
			(6
				(Face mosely ego)
				(ego view: 2401 loop: 0 cel: 6 setCycle: BegLoop self)
			)
			(7
				(GKEgo normalize: 0)
				(ego setMotion: PolyPath 209 128 self)
				(franky
					init:
					view: 2361
					setCycle: StopWalk -1
					setMotion: PolyPath 108 102 self
				)
			)
			(8 (Face franky mosely))
			(9 (Face ego 169 152 self))
			(10
				(ego hide:)
				(mosely
					view: 242
					loop: 1
					setCycle: 0
					cel: 0
					posn: 215 129
				)
				(= cycles 3)
			)
			(11
				(messager say: 27 0 68 2 4 self)
			)
			(12
				(franky setMotion: PolyPath 169 152 self)
			)
			(13 (Face franky 100 123 self))
			(14
				(franky
					view: 242
					loop: 5
					cel: 0
					posn: 169 152
					setCycle: EndLoop self
				)
			)
			(15
				(messager say: 27 0 68 5 self)
			)
			(16 (mosely setCycle: EndLoop self))
			(17
				(franky loop: 6 cel: 0 setCycle: EndLoop self)
			)
			(18
				(franky loop: 5 cel: 4 setCycle: BegLoop self)
			)
			(19
				(messager say: 27 0 68 6 self)
			)
			(20
				(photoTeller doVerb: 11)
				(self dispose:)
			)
		)
	)
)

(instance photoReplay of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(mosChair init:)
				(ego
					view: 900
					loop: 2
					init:
					posn: 103 88
					setCycle: StopWalk -1
					setMotion: PolyPath 209 128 self
				)
				(franky view: 242 loop: 5 cel: 0 init: posn: 169 152)
				(= cycles 5)
			)
			(1 (messager say: 27 0 69 1))
			(2 (Face ego franky self))
			(3
				(ego hide:)
				(mosely
					view: 242
					loop: 1
					setCycle: 0
					cel: 0
					posn: 215 129
				)
				(= cycles 2)
			)
			(4
				(messager say: 27 0 69 2 self)
			)
			(5
				(franky view: 242 loop: 5 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(6
				(franky loop: 5 cel: 4)
				(= cycles 6)
			)
			(7
				(mosely setCycle: BegLoop self)
				(franky view: 242 loop: 5 cel: 4 setCycle: BegLoop self)
				(messager say: 27 0 69 3)
			)
			(8)
			(9
				(mosely
					view: 245
					loop: 8
					cel: 1
					posn: 225 129
					ignoreActors: 1
					ignoreControl: -32768
				)
				(GKEgo normalize: 5)
				(ego show:)
				(franky
					view: 2361
					loop: -1
					loop: 6
					setCycle: StopWalk -1
					setMotion: PolyPath 108 102 self
				)
				(messager say: 27 0 69 4)
			)
			(10
				(ego setScript: setThemDown self)
				(franky setMotion: PolyPath 92 88 self)
			)
			(11 (franky dispose:))
			(12
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance crashInterview of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: 900 posn: 85 73 init:)
				(GKEgo normalize:)
				(crash init:)
				(copper init:)
				(mosChair init:)
				(= cycles 3)
			)
			(1
				(mosely loop: 0 cel: 0 setCycle: Oscillate 1 self)
			)
			(2
				(Prints {Ego walks thru the door here.})
				(= cycles 3)
			)
			(3
				(mosely loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(4
				(ego setMotion: PolyPath 96 84)
				(mosely
					view: 245
					posn: 158 135
					setCycle: StopWalk -1
					setMotion: PolyPath 104 92 self
				)
			)
			(5
				(messager say: 27 0 65 1 5 self)
			)
			(6
				(ego setMotion: PolyPath 110 133 self)
				(mosely setMotion: PolyPath 158 135 self)
			)
			(7)
			(8
				(Face ego crash)
				(mosely view: 244 loop: 2 cel: 4 setCycle: BegLoop self)
			)
			(9
				(messager say: 27 0 65 6 self)
				(mosely loop: 1 cel: 0 setCycle: Oscillate 2 self)
			)
			(10)
			(11
				(messager say: 27 0 65 7 self)
				(crash loop: 1 cel: 0 setCycle: ForwardCounter 2 self)
			)
			(12)
			(13
				(messager say: 27 0 65 8 self)
				(mosely loop: 1 cel: 0 setCycle: Oscillate 2 self)
			)
			(14)
			(15
				(messager say: 27 0 65 9 self)
				(crash loop: 1 cel: 0 setCycle: ForwardCounter 2 self)
			)
			(16)
			(17
				(messager say: 27 0 65 10 self)
				(mosely loop: 1 cel: 0 setCycle: Oscillate 2 self)
			)
			(18)
			(19
				(messager say: 27 0 65 11 self)
				(crash loop: 1 cel: 0 setCycle: ForwardCounter 2 self)
			)
			(20)
			(21
				(messager say: 27 0 65 12 self)
				(mosely loop: 1 cel: 0 setCycle: Oscillate 2 self)
			)
			(22)
			(23
				(messager say: 27 0 65 13 self)
				(crash loop: 1 cel: 0 setCycle: ForwardCounter 2 self)
			)
			(24)
			(25
				(mosely loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(26 (= seconds 3))
			(27
				(messager say: 27 0 65 14 self)
			)
			(28
				(messager say: 27 0 65 15 self)
				(crash loop: 2 cel: 0 setCycle: ForwardCounter 2 self)
			)
			(29)
			(30
				(messager say: 27 0 65 16 self)
				(mosely loop: 1 cel: 0 setCycle: Oscillate 2 self)
			)
			(31)
			(32
				(messager say: 27 0 65 17 self)
				(crash loop: 0 cel: 0 cycleSpeed: 10 setCycle: Oscillate 4 self)
			)
			(33)
			(34
				(mosely loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(35
				(mosely view: 245 loop: 8 cel: 7)
				(messager say: 27 0 65 18 self)
				(Face ego copper)
			)
			(36
				(copper
					view: 249
					setLoop: 0
					posn: 67 109
					illegalBits: 0
					setCycle: Walk
					ignoreActors:
					setMotion: MoveTo 122 106 self
				)
			)
			(37
				(copper setMotion: MoveTo 157 112 self)
			)
			(38
				(copper hide:)
				(crash view: 2442 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(39
				(copper
					view: 249
					setLoop: 1
					posn: 166 117
					setCycle: Walk
					show:
					setMotion: MoveTo 105 84 self
				)
				(crash loop: 1 cel: 0 posn: 107 85 setCycle: EndLoop self)
			)
			(40
				(crash dispose:)
				(copper dispose:)
				(Face ego mosely self)
				(Face mosely ego)
			)
			(41
				(messager say: 27 0 65 19 23 self)
			)
			(42
				(self setScript: setThemDown self)
			)
			(43
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance setThemDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(GKEgo normalize:)
				(ego
					ignoreActors: 1
					ignoreControl: -32768
					setMotion: PolyPath 133 124 self
				)
			)
			(1
				(ego view: 2401 loop: 0 cel: 0 setCycle: EndLoop self)
				(mosely
					view: 245
					setCycle: StopWalk -1
					setMotion: PolyPath 217 126 self
				)
			)
			(2)
			(3
				(mosChair dispose:)
				(mosely
					view: 243
					posn: 234 123
					loop: 1
					cel: 5
					setCycle: BegLoop self
				)
			)
			(4
				(mosely view: 240 loop: (Random 0 3) cel: 0)
				(= cycles 3)
			)
			(5
				(= local4 1)
				(frankyInWindow init: setScript: frankyAtFiles)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance caseClosed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(messager say: 27 0 66 0 self)
			)
			(2 (self dispose:))
		)
	)
)

(instance giveTheBadgeBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 900
					loop: 2
					posn: 103 88
					setCycle: StopWalk -1
					init:
					setMotion: PolyPath 133 124 self
				)
				(messager say: 27 0 70 1)
			)
			(1
				(messager say: 27 0 70 2 self)
				(ego view: 2401 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(2)
			(3
				(messager say: 27 0 70 3 6 self)
			)
			(4
				(mosely view: 243 loop: 3 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(5
				(mosChair init:)
				(mosely loop: 5 cel: 0 setCycle: EndLoop self)
			)
			(6
				(messager say: 27 0 70 7)
				(mosely
					loop: 6
					cel: 0
					cycleSpeed: 10
					setCycle: Oscillate 5 self
				)
			)
			(7
				(mosely loop: 7 cel: 0 setCycle: EndLoop self)
			)
			(8
				(messager say: 27 0 70 8 self)
				(mosely view: 243 loop: 3 cel: 4 setCycle: BegLoop self)
			)
			(9 (mosely loop: 1 cel: 0))
			(10
				(messager say: 27 0 70 9 self)
				(ego view: 2401 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(11)
			(12
				(ego loop: 0 cel: 6 put: 16)
				(mosely view: 240 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(13
				(mosely setCycle: BegLoop self)
				(self dispose:)
			)
		)
	)
)

(instance goFixHair of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 21 47 59 0 self)
			)
			(1 (mosely setCycle: BegLoop self))
			(2
				(mosely
					view: 245
					loop: 8
					cel: 5
					posn: 223 128
					ignoreActors: 1
				)
				(GKEgo normalize:)
				(ego show: ignoreActors: 1 setMotion: PolyPath 92 88 self)
			)
			(3
				(Bset 34)
				(Bset 20)
				(curRoom newRoom: 230)
			)
		)
	)
)

(instance nothingMore of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local3
					(self cue:)
				else
					(messager say: 21 47 62 1 4 self)
				)
			)
			(1 (mosely setCycle: BegLoop self))
			(2
				(mosely
					view: 245
					loop: 8
					cel: 1
					posn: 225 129
					ignoreActors: 1
					ignoreControl: -32768
				)
				(GKEgo normalize: 5)
				(ego show:)
				(franky
					view: 2361
					loop: -1
					loop: 6
					setCycle: StopWalk -1
					setMotion: PolyPath 108 102 self
				)
			)
			(3
				(ego setScript: setThemDown self)
				(franky setMotion: PolyPath 92 88 self)
			)
			(4 (franky dispose:))
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance reOpenCase of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Prints {reopen case.})
				(self dispose:)
			)
		)
	)
)

(instance mosLeaveRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(mosely view: 243 loop: 3 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(1
				(mosChair init:)
				(mosely
					view: (if (Btst 98) 2451 else 245)
					setLoop: -1
					setLoop: GradualLooper
					posn: 217 126
					setCycle: StopWalk -1
					setMotion: PolyPath 110 95 self
				)
			)
			(2
				(Face mosely ego)
				(= cycles 15)
			)
			(3
				(cond 
					((== register 64) (messager say: 27 0 64 1 self))
					((== register 63) (messager say: 27 0 63 1 self))
					(else (self cue:))
				)
			)
			(4
				(mosely
					setCycle: StopWalk -1
					setMotion: PolyPath 100 80 self
				)
			)
			(5
				(theGame handsOn:)
				(= moselyInRoom 0)
				(mosely hide:)
				(= seconds 10)
			)
			(6
				(theGame handsOff:)
				(= moselyInRoom 1)
				(if (not (== (ego script?) stealTheBadge))
					(client setScript: bringInCoffee)
				)
				(self dispose:)
			)
		)
	)
)

(instance bringInCoffee of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(mosely
					view: (if (Btst 98) 2451 else 245)
					posn: 100 80
					show:
					setLoop: -1
					setLoop: GradualLooper
					setCycle: StopWalk -1
					setMotion: PolyPath 107 124 self
				)
			)
			(1
				(mosely hide:)
				(ego
					view: 2402
					loop: 1
					cycleSpeed: 12
					cel: 1
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(messager say: 27 0 72 1)
				(ego view: 2402 cycleSpeed: 6 loop: 2)
				(mosely
					posn: 111 125
					show:
					setMotion: PolyPath 224 126 self
				)
			)
			(3
				(messager say: 27 0 72 2)
				(ego loop: 3 cel: 0 setCycle: EndLoop self)
				(mosChair dispose:)
				(mosely
					view: 243
					loop: 1
					cel: 8
					posn: 227 120
					setCycle: BegLoop self
				)
			)
			(4)
			(5
				(mosely
					view: 240
					loop: (Random 0 3)
					cel: 0
					posn: 234 123
				)
				(ego loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(6
				(theGame handsOn:)
				(ego view: 2401 loop: 0 cel: 6)
				(self dispose:)
			)
		)
	)
)

(instance stealTheBadge of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= gabrielSitting 0)
				(ego view: 2401 loop: 0 cel: 6 setCycle: BegLoop self)
			)
			(1
				(GKEgo normalize: 0)
				(ego setMotion: PolyPath 212 126 self)
			)
			(2
				(mosChair hide:)
				(messager say: 1 12 4 1)
				(ego view: 241 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(3
				(messager say: 1 12 4 2)
				(mosChair show:)
				(GKEgo normalize:)
				(ego get: 16 setMotion: PolyPath 133 124 self)
			)
			(4
				(messager say: 1 12 4 3)
				(ego view: 2401 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(5
				(curRoom setScript: bringInCoffee)
				(self dispose:)
			)
		)
	)
)

(instance tossCoffee of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: 2401 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(1
				(ego view: 2402 loop: 0 cel: 0)
				(self dispose:)
			)
		)
	)
)

(instance getBadge of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 0
					loop: 0
					setCycle: StopWalk -1
					setMotion: PolyPath 219 123 self
				)
			)
			(1
				(ego view: 241 loop: 1 cel: 0 get: 16 setCycle: EndLoop self)
			)
			(2
				(ego
					view: 900
					loop: -1
					loop: 0
					setCycle: StopWalk -1
					setMotion: PolyPath 114 125 self
				)
			)
			(3
				(ego view: 2401 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(4
				(ego view: 2402 loop: 0 cel: 0)
				(self dispose:)
			)
		)
	)
)

(instance getTracker of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 210 126 self)
			)
			(1 (Face ego 205 126 self))
			(2
				(ego
					view: 246
					loop: 0
					cel: 0
					posn: 207 126
					setCycle: EndLoop self
				)
			)
			(3
				(ego loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(4
				(ego loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(5
				(ego
					view: 900
					loop: -1
					loop: 1
					setCycle: StopWalk -1
					setMotion: PolyPath 190 135
				)
				(self dispose:)
			)
		)
	)
)

(instance moveToMosely of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: 2401 loop: 1 cel: 0 setCycle: CycleTo 2 1 self)
			)
			(1
				(mosely view: 240 loop: 3 cel: 0 setCycle: EndLoop self)
				(ego cel: 3 setCycle: EndLoop self)
			)
			(2)
			(3
				(ego loop: 0 cel: 6)
				(= cycles 1)
			)
			(4
				(switch register
					(50
						(messager say: 17 50 0 0 self)
					)
					(42
						(cond 
							((Btst 16)
								(if (Btst 25)
									(messager say: 17 42 23 0 self)
								else
									(messager say: 17 42 22 0 self)
								)
							)
							((<= currentDay 3) (messager say: 17 42 14 0 self))
							(else (messager say: 17 42 16 0 self))
						)
						(Bset 25)
					)
					(19
						(if (Btst 26) (messager say: 17 19 24 0 self))
						(if (Btst 16)
							(if (not (Btst 27))
								(if (Btst 28)
									(messager say: 17 19 28 0 self)
								else
									(messager say: 17 19 27 0 self)
								)
							else
								(messager say: 17 19 29 0 self)
							)
						else
							(messager say: 17 19 16 0 self)
						)
						(Bset 27)
					)
					(20
						(cond 
							((Btst 16)
								(if (Btst 29)
									(messager say: 17 20 32 0 self)
								else
									(messager say: 17 20 31 0 self)
								)
							)
							((<= currentDay 3) (messager say: 17 20 14 0 self))
							(else (messager say: 17 20 16 0 self))
						)
						(Bset 29)
					)
					(26
						(if (Btst 16)
							(if (Btst 30)
								(messager say: 17 26 35 0 self)
							else
								(messager say: 17 26 34 0 self)
							)
						else
							(messager say: 17 26 16 0 self)
						)
						(Bset 30)
					)
					(41
						(cond 
							((Btst 16)
								(cond 
									((Btst 33) (messager say: 17 41 36 0 self))
									((Btst 26) (messager say: 17 41 37 0 self))
									(else (messager say: 17 41 39 0 self))
								)
							)
							((<= currentDay 3) (messager say: 17 41 14 0 self))
							(else (messager say: 17 41 16 0 self))
						)
						(Bset 105)
					)
					(30
						(cond 
							((Btst 16)
								(if (Btst 31)
									(messager say: 17 30 48 0 self)
								else
									(messager say: 17 30 47 0 self)
								)
							)
							((Btst 31) (messager say: 17 30 46 0 self))
							(else (messager say: 17 30 44 0 self))
						)
						(Bset 31)
					)
					(51
						(if (Btst 16)
							(cond 
								((Btst 32) (messager say: 17 51 52 0 self))
								((Btst 26) (messager say: 17 51 50 0 self))
								(else (messager say: 17 51 51 0 self))
							)
						else
							(messager say: 17 51 16 0 self)
						)
						(Bset 32)
					)
					(16
						(if (not (Btst 26))
							(cond 
								((Btst 16)
									(cond 
										((Btst 28) (messager say: 17 16 57 0 self))
										((Btst 27) (messager say: 17 16 56 0 self))
										(else (messager say: 17 16 55 0 self))
									)
								)
								((<= currentDay 3) (messager say: 17 16 14 0 self))
								(else (messager say: 17 16 16 0 self))
							)
						else
							(messager say: 17 16 24 0 self)
						)
						(Bset 28)
					)
				)
			)
			(5
				(ego view: 2401 loop: 1 cel: 0 setCycle: CycleTo 5 1 self)
				(mosely view: 240 loop: 3 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(6)
			(7
				(mosely view: 240 loop: 3 cel: 5 setCycle: EndLoop self)
				(ego cel: 6 setCycle: EndLoop self)
			)
			(8)
			(9
				(if
					(and
						(Btst 28)
						(Bset 31)
						(Bset 105)
						(Bset 27)
						(Bset 25)
						(or (Bset 30) (Bset 29) (Bset 32))
					)
					(mosely setScript: walkToTheDoor)
				)
				(ego loop: 0 cel: 6)
				(self dispose:)
			)
		)
	)
)

(instance frankyAtFiles of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(frankyInWindow hide:)
				(= seconds (Random 5 20))
			)
			(1
				(frankyInWindow show:)
				(frankyInWindow
					loop: 4
					setCycle: Walk
					setMotion: MoveTo 60 90 self
				)
			)
			(2
				(frankyInWindow view: 248 loop: 0 setCycle: EndLoop self)
			)
			(3
				(frankyInWindow loop: 1 cel: 0 setCycle: Oscillate 1 self)
			)
			(4
				(frankyInWindow loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(5 (= seconds 3))
			(6
				(frankyInWindow setCycle: BegLoop self)
			)
			(7
				(frankyInWindow loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(8
				(frankyInWindow loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(9
				(frankyInWindow
					view: 2361
					loop: 7
					posn: 52 90
					setCycle: Walk
					setMotion: MoveTo 23 48 self
				)
			)
			(10 (self init:))
		)
	)
)

(instance takeAnotherPic of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local3
					(messager say: 21 47 61 0 self)
				else
					(messager say: 21 47 58 1 3 self)
				)
			)
			(1
				(if local3
					(mosely setScript: nothingMore)
					(self dispose:)
				else
					(franky loop: 6 cel: 0 setCycle: EndLoop self)
				)
			)
			(2
				(franky loop: 5 cel: 4 setCycle: BegLoop self)
			)
			(3
				(messager say: 21 47 58 4 self)
				(= local3 1)
			)
			(4 (photoTeller doVerb: 11))
		)
	)
)

(instance walkToTheDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 27 0 18 1 3 self)
				(mosely view: 243 loop: 3 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(1
				(messager say: 27 0 18 4)
				(mosChair init:)
				(mosely
					view: 245
					loop: egoLooper
					posn: 217 126
					setCycle: StopWalk -1
					setMotion: PolyPath 104 90 self
				)
			)
			(2
				(curRoom newRoom: 230)
				(self dispose:)
			)
		)
	)
)

(instance photoTeller of GKTeller
	(properties
		curNoun 19
		sayNoun 21
		verb 47
	)
	
	(method (doVerb theVerb)
		(return
			(if local4
				(client doVerb: theVerb)
				(return 1)
			else
				(SetCursor -2)
				(theGame setCursor: 999 1)
				(if (Btst 2)
					(self verb: 52 newNoun: 20)
				else
					(self verb: 47 newNoun: 19)
				)
				(repeat
					(if (self respond:) (break))
				)
				(SetCursor 0 0 153 319)
				(theGame setCursor: local7 1)
				(return 1)
			)
		)
	)
	
	(method (sayMessage)
		(switch iconValue
			(59
				(curRoom setScript: goFixHair)
			)
			(60
				(messager say: 21 47 60 0 self)
			)
			(58
				(curRoom setScript: takeAnotherPic)
			)
			(61
				(curRoom setScript: takeAnotherPic)
			)
			(62
				(curRoom setScript: nothingMore)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(super showCases: 58 (not local3) 61 local3)
	)
	
	(method (cue)
		(if (== iconValue 60)
			(self doVerb: 11)
		else
			(self newNoun: 20)
		)
	)
)

(instance mosely of Actor
	(properties
		noun 17
		signal $5000
		illegalBits $0000
	)
	
	(method (init)
		(= moselyInRoom 1)
		(cond 
			((Btst 34)
				(self
					view: 245
					loop: 8
					cel: 5
					posn: 223 128
					ignoreActors: 1
				)
			)
			((and (== currentDay 3) (not (Btst 21))) (self view: 244 loop: 2 posn: 160 134 cel: 0))
			(else
				(self
					view: 240
					posn: 234 123
					loop: (Random 0 2)
					setCycle: EndLoop
				)
			)
		)
		(super init:)
	)
	
	(method (dispose)
		(= moselyInRoom 0)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(11
				(switch moselyTalkCount
					(1 (= temp0 40))
					(2 (= temp0 41))
					(3 (= temp0 42))
					(else  (= temp0 43))
				)
				(messager say: noun theVerb temp0 0)
				(++ moselyTalkCount)
			)
			(10
				(= interrogateSubject 8)
				(curRoom newRoom: 50)
			)
			(50
				(curRoom setScript: moveToMosely 0 50)
			)
			(42
				(curRoom setScript: moveToMosely 0 42)
			)
			(19
				(curRoom setScript: moveToMosely 0 19)
			)
			(20
				(curRoom setScript: moveToMosely 0 19)
			)
			(26
				(curRoom setScript: moveToMosely 0 19)
			)
			(41
				(curRoom setScript: moveToMosely 0 19)
			)
			(30
				(curRoom setScript: moveToMosely 0 19)
			)
			(51
				(curRoom setScript: moveToMosely 0 19)
			)
			(16
				(curRoom setScript: moveToMosely 0 19)
			)
			(24
				(messager say: noun theVerb 0 0)
			)
			(48
				(messager say: noun theVerb 0 0)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance frankyInWindow of Actor
	(properties
		x 23
		y 48
		noun 11
		view 2361
		signal $6000
		cycleSpeed 8
		illegalBits $0000
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(7
				(if sawFrankyInWindow
					(= temp0 13)
				else
					(= sawFrankyInWindow 1)
					(= temp0 12)
				)
				(messager say: noun theVerb temp0 0)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance franky of Actor
	(properties
		noun 23
		signal $6000
		illegalBits $0000
	)
	
	(method (init)
		(if (Btst 34)
			(self view: 242 loop: 5 cel: 0 posn: 96 127)
		else
			(self view: 2361 loop: 2 posn: 93 75)
		)
		(super init:)
	)
)

(instance crash of Actor
	(properties
		x 129
		y 123
		view 2441
		signal $4000
	)
)

(instance copper of Actor
	(properties
		view 237
		signal $4000
	)
	
	(method (init)
		(if (and (== currentDay 3) (not (Btst 21)))
			(self view: 244 loop: 3 posn: 68 110 cel: 0)
		)
		(super init:)
	)
)

(instance theDoor of Prop
	(properties
		x 112
		y 80
		noun 22
		view 2400
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6 (ego setScript: standGabUp))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance mosChair of View
	(properties
		x 230
		y 120
		noun 15
		view 2400
		signal $6000
	)
	
	(method (init)
		(if (Btst 98)
			(self cel: 1 noun: 16)
		else
			(self cel: 0 noun: 15)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(12
				(cond 
					(moselyInRoom (super doVerb: theVerb))
					((Btst 98) (ego setScript: stealTheBadge))
					(else (super doVerb: theVerb))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bookshelf of Feature
	(properties
		x 165
		y 96
		noun 4
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						150
						22
						204
						27
						206
						35
						202
						37
						202
						48
						206
						52
						206
						66
						198
						75
						179
						80
						162
						80
						156
						94
						145
						92
					yourself:
				)
		)
		(super init:)
	)
)

(instance microwave of Feature
	(properties
		x 280
		y 120
		noun 13
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 306 75 307 84 301 85 301 93 295 95 274 91 273 78 287 71
					yourself:
				)
		)
		(super init:)
	)
)

(instance fridge of Feature
	(properties
		x 280
		y 120
		noun 14
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 267 94 272 91 298 95 276 119 268 119
					yourself:
				)
		)
		(super init:)
	)
)

(instance bullBoard of Feature
	(properties
		x 250
		y 106
		noun 5
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 232 44 277 50 279 77 231 71
					yourself:
				)
		)
		(super init:)
	)
)

(instance desk of Feature
	(properties
		x 200
		y 110
		noun 7
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						186
						101
						161
						95
						160
						84
						166
						80
						179
						82
						180
						87
						205
						75
						239
						81
						244
						99
						235
						106
						222
						105
						221
						93
						203
						99
						205
						105
						201
						105
						204
						115
						186
						122
					yourself:
				)
		)
		(super init:)
	)
)

(instance waste of Feature
	(properties
		x 146
		y 132
		noun 31
	)
	
	(method (init)
		(super init:)
		(self setOnMeCheck: 1 4)
	)
)

(instance leftFileCab of Feature
	(properties
		x 69
		y 106
		noun 10
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 22 98 14 98 15 88 76 71 95 74 96 98 64 108
					yourself:
				)
		)
		(super init:)
	)
)

(instance leftPlant of Feature
	(properties
		x 5
		y 189
		noun 25
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						1
						66
						13
						53
						8
						48
						0
						47
						2
						32
						13
						32
						22
						30
						33
						21
						49
						21
						56
						37
						37
						23
						26
						30
						30
						34
						35
						33
						53
						44
						52
						50
						39
						39
						25
						51
						45
						55
						58
						66
						46
						61
						46
						65
						23
						54
						12
						58
						22
						60
						30
						69
						37
						85
						16
						66
						10
						71
						17
						76
						19
						86
						14
						89
						13
						102
						0
						104
					yourself:
				)
		)
		(super init:)
	)
)

(instance rightPlant of Feature
	(properties
		x 306
		y 115
		noun 25
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						318
						142
						276
						125
						276
						120
						280
						115
						280
						109
						270
						111
						275
						106
						286
						106
						290
						104
						284
						102
						294
						101
						300
						101
						315
						95
						308
						90
						319
						88
					yourself:
				)
		)
		(super init:)
	)
)

(instance winder of Feature
	(properties
		x 60
		y 80
		noun 32
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 33 39 80 30 80 65 32 77
					yourself:
				)
		)
		(super init:)
	)
)

(instance leftBinder of Feature
	(properties
		x 55
		y 123
		noun 2
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 0 144 0 104 19 99 77 110 67 115 72 125 78 125 79 129 38 143
					yourself:
				)
		)
		(super init:)
	)
)

(instance rightBinder of Feature
	(properties
		x 260
		y 123
		noun 2
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 237 132 242 134 242 130 265 123 313 144 213 143
					yourself:
				)
		)
		(super init:)
	)
)

(instance birthday of Feature
	(properties
		x 60
		y 100
		noun 3
	)
	
	(method (init)
		(super init:)
		(self setOnMeCheck: 1 128)
	)
)

(instance deskLamp of Feature
	(properties
		x 191
		y 80
		noun 8
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						185
						82
						180
						71
						197
						75
						200
						74
						206
						77
						192
						81
						190
						79
						192
						77
						184
						74
						189
						83
						194
						84
						186
						87
						182
						86
					yourself:
				)
		)
		(super init:)
	)
)

(instance trumPost of Feature
	(properties
		x 296
		y 114
		noun 29
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 284 39 316 45 315 76 286 70
					yourself:
				)
		)
		(super init:)
	)
)

(instance speaker of Feature
	(properties
		x 210
		y 100
		noun 12
		nsTop 34
		nsLeft 207
		nsBottom 48
		nsRight 220
	)
)

(instance rightFileCab of Feature
	(properties
		x 166
		y 114
		noun 10
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 149 104 160 97 185 101 186 123 167 131 147 128
					yourself:
				)
		)
		(super init:)
	)
)

(instance falconPoster of Feature
	(properties
		x 140
		y 40
		noun 9
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 145 55 133 52 132 30 145 30
					yourself:
				)
		)
		(super init:)
	)
)

(instance gabsChair of Feature
	(properties
		x 134
		y 99
		noun 30
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 122 108 118 89 124 85 130 101 143 102 143 115 140 120 121 119
					yourself:
				)
		)
		(super init:)
	)
)
