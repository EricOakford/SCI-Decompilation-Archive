;;; Sierra Script 1.0 - (do not remove this comment)
(script# 3)
(include game.sh)
(use Main)
(use Intrface)
(use tahiti)
(use EgoDead)
(use LoadMany)
(use DPath)
(use Chase)
(use Grooper)
(use Sight)
(use Avoider)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	volleyRm 0
	Man 1
	Girl 2
	Ball 3
	Net 4
	joinGameScript 5
	Girl2 6
	HighPrint 7
	TimeHighPrint 8
)

(local
	local0
	local1
	egoViewer
	[local3 2]
	local5
	local6
	local7
)
(procedure (HighPrint &tmp [sizeRect 4] [str 100])
	(cls)
	(Format @str &rest)
	(TextSize @[sizeRect 0] @str userFont 0)
	(Print @str
		#at -1 12
		#width (if (> [sizeRect 2] 24) 300 else 0)
		#mode teJustCenter
	)
)

(procedure (TimeHighPrint numSeconds &tmp [sizeRect 4] [str 100])
	(cls)
	(Format @str &rest)
	(TextSize @[sizeRect 0] @str userFont 0)
	(Print @str
		#at -1 12
		#width (if (> [sizeRect 2] 24) 300 else 0)
		#mode teJustCenter
		#dispose
		#time numSeconds
	)
)

(procedure (KissGirl)
	(switch (Random 1 3)
		(1 (Print 3 30))
		(2 (Print 3 31))
		(3 (Print 3 32))
	)
)

(instance volleyRm of Room
	(properties
		picture 3
		east 2
		south 8
		west 4
		picAngle 70
	)
	
	(method (init)
		(super init:)
		DPath
		Avoider
		Chase
		Jump
		(LoadMany SCRIPT 301 355 354 368)
		(LoadMany VIEW 3 206 503 603 803 203 103 403)
		(LoadMany SOUND 38 39 40 41 53)
		(self setRegions: 301 300)
		(globalSound stop:)
		(= egoViewer (ego viewer?))
		(switch prevRoomNum
			(south)
			(west
				(ego
					loop: 0
					posn:
						10
						(switch (ego view?)
							(217 180)
							(216 155)
							(215 130)
							(214 125)
							(else  110)
						)
				)
			)
			(else 
				(ego
					posn:
						300
						(switch (ego view?)
							(217 180)
							(216 140)
							(215 120)
							(214 115)
							(else  100)
						)
					heading: 270
				)
				(DirLoop ego 270)
			)
		)
		(HandsOn)
		(addToPics add: Net doit:)
		(self setFeatures: Net)
		(ego init:)
		(self setScript: volleyScript)
	)
	
	(method (doit)
		(super doit: &rest)
		(if local5
			(= local5 0)
			(ego setScript: (ScriptID 368) volleyScript)
		)
	)
	
	(method (handleEvent event &tmp [temp0 35])
		(cond 
			((super handleEvent: event))
			((Said 'look[<around,at][/room,beach]')
				(if (tahiti volley?)
					(HighPrint 3 1)
				else
					(HighPrint 3 2)
				)
			)
		)
	)
	
	(method (newRoom nRoom)
		(cond 
			(local6)
			((and (== nRoom south) local1)
				(if (lifesaverScript register?)
					(super newRoom: nRoom &rest)
				else
					(HandsOff)
					(lifesaverScript register: TRUE)
					(ego setMotion: MoveTo (ego x?) 230 script)
					(= local6 1)
				)
			)
			(local1
				(EgoDead 918 0 0 3 0)
			)
			(else
				(super newRoom: nRoom &rest)
			)
		)
	)
	
	(method (notify param1 param2)
		(-- argc)
		(switch param1
			(0
				(if argc (= local1 param2) else local1)
			)
			(1
				(if argc (= local0 param2) else local0)
			)
			(2 (= local5 1))
			(3
				(if argc (= local7 param2) else local7)
			)
			(4
				(ego
					view: 200
					setLoop: -1
					setCycle: Walk
					loop: 0
					viewer: egoViewer
				)
			)
		)
	)
)

(instance volleyScript of Script
	
	(method (init param1)
		(if
		(or (< 7 (tahiti volley?)) (== (ego view?) 206))
			(Animate (cast elements?) TRUE)
			(= state start)
			(self dispose:)
		else
			(Man init:)
			(Girl init:)
			(Girl2 init:)
			(Ball init:)
			(Animate (cast elements?) TRUE)
			(super init: param1 &rest)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (Random 0 1)
					(Ball
						illegalBits: 0
						ignoreHorizon: 1
						posn: 151 66
						setPri: (+ (Girl priority?) 1)
						setMotion: JumpTo (Girl nsRight?) (Girl nsTop?) self
					)
					(self setScript: (ScriptID 355 0) self Man)
				else
					(Ball
						illegalBits: 0
						ignoreHorizon: TRUE
						posn: 172 71
						setPri: (+ (Man priority?) 1)
						setMotion: JumpTo (Man nsLeft?) (Man nsTop?) self
					)
					(self setScript: (ScriptID 355 0) self Girl)
				)
			)
			(1
				(if (not (tahiti volley?))
					(TimeHighPrint 4 3 3)
				)
				(theGame changeScore: 1)
				(tahiti volley: (+ (tahiti volley?) 1))
				(= seconds 5)
			)
			(2
				(if local0
					(theGame changeScore: 1)
					(tahiti volley: (+ (tahiti volley?) 1))
					(= seconds 5)
				)
			)
			(3
				(if (and local0 (< 7 (tahiti volley?)))
					(= cycles 2)
				else
					(self changeState: 2)
				)
			)
			(4
				(soundSplash init:)
				(script caller: self)
			)
			(5)
			(6
				(soundSplash number: (SoundFX 53) play:)
				(splash init: posn: (Ball x?) (Ball y?) setCycle: EndLoop)
				(Ball setMotion: JumpTo (+ (Ball x?) 40) 260 self)
			)
			(7
				(HighPrint 3 4)
				(= local7 0)
				(= cycles 4)
			)
			(8
				(DisposeScript 355)
				(= local1 1)
				(HandsOff)
				(splash dispose:)
				(Girl
					heading: 180
					view: 203
					setAvoider: Avoider 1
					setCycle: Walk
					setMotion: Chase Ball 10 self
				)
				(ego setCycle: Walk)
			)
			(9
				(HandsOn)
				(ego setScript: lifesaverScript)
				(= seconds 1)
			)
			(10)
			(11
				(ego setScript: (ScriptID 354))
			)
			(12
				(= local6 0)
				(= local1 0)
				(ego setCycle: Walk viewer: egoViewer setLoop: GradualLooper)
			)
		)
	)
)

(instance joinGameScript of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(User canControl: 0)
				(= local0 0)
				(ego
					setLoop: -1
					setAvoider: Avoider
					setMotion: DPath 100 116 121 116 self
				)
			)
			(1
				(ego
					setAvoider: 0
					viewer: 0
					view: 3
					setLoop: 1
					setCel: 0
					heading: 90
				)
				(= local0 1)
				(self dispose:)
			)
		)
	)
)

(instance lifesaverScript of Script
	
	(method (dispose)
		(if (or (== state 2) (!= curRoomNum newRoomNum)) 1)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HighPrint 3 5)
				(HandsOn)
				(ego setCycle: Walk viewer: egoViewer)
				(= seconds 12)
			)
			(1
				(HighPrint 3 6)
				(= seconds 8)
			)
			(2
				(HighPrint 3 7)
				(= seconds 8)
			)
			(3
				(if (not register)
					(lifesaverScript register: 1)
					(Print 3 8)
					(theGame changeScore: -10)
					(= local1 0)
				)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'save,help')
				(Print 3 9)
			)
		)
	)
)

(instance AngryManScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(if (and (< 160 (ego x?)) (< (ego y?) 150))
					(HighPrint 3 10)
					(= seconds 15)
				else
					(= seconds 1)
				)
			)
			(2
				(if (and (< 160 (ego x?)) (< (ego y?) 150))
					(HighPrint 3 11)
					(self init:)
				else
					(self changeState: 1)
				)
			)
		)
	)
)

(instance GirlViewer of Code

	(method (doit)
		(if (!= (Girl onControl: origin) cBLACK)
			(Girl view: 103)
		)
		(Girl
			setLoop:
				(switch (Girl view?)
					(3 3)
					(103
						(switch (Girl onControl: origin)
							(cLBLUE 3)
							(cLGREEN 3)
							(cLCYAN 2)
							(cLRED 1)
							(cBLACK 0)
							(else  (Girl loop?))
						)
					)
					(203 -1)
				)
		)
	)
)

(instance Girl of Actor
	(properties
		y 121
		x 135
		heading 90
		view 3
		loop 3
	)
	
	(method (init)
		(super init: &rest)
		(= viewer GirlViewer)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/babe]')
				(HighPrint 3 12)
			)
			((Said 'kiss[/babe]')
				(KissGirl)
			)
			((not local7))
			((Said 'address/babe')
				(if local7
					(HighPrint 3 13)
					(HighPrint 3 14)
					(HighPrint 3 15)
				else
					(HighPrint 3 16)
				)
			)
		)
	)
	
	(method (canBeHere)
		(return TRUE)
	)
)

(instance Ball of Actor
	(properties
		view 3
		loop 7
	)
	
	(method (init)
		(self setLoop: loop)
		(super init:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event &rest))
			((or (Said '/ball>') (Said '//ball>'))
				(cond 
					((and (CantBeSeen self) (Said '/*'))
						(HighPrint 3 17)
						(event claimed: TRUE)
					)
					((IsOffScreen self)
						(HighPrint 3 18)
						(event claimed: TRUE)
					)
					((Said 'look[<at]')
						(HighPrint 3 19)
					)
				)
			)
		)
	)
)

(instance Girl2 of Actor
	(properties
		y 117
		x 174
		view 3
		loop 4
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/babe]')
				(HighPrint 3 20)
			)
			((Said 'address/babe')
				(if local0
					(if local1
						(TimeHighPrint 4 3 21)
					else
						(TimeHighPrint 4 3 22)
					)
				else
					(TimeHighPrint 4 3 23)
				)
			)
			((Said 'help,get,save/babe')
				(if local1 (TimeHighPrint 4 3 24)
				else
					(event claimed: FALSE)
				)
			)
			((Said 'kiss[/babe]')
				(KissGirl)
			)
		)
	)
	
	(method (canBeHere)
		(return TRUE)
	)
)

(instance Man of Actor
	(properties
		y 122
		x 188
		view 3
		loop 5
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/man]')
				(HighPrint 3 25)
			)
			((Said 'address/man')
				(cond 
					(local1
						(TimeHighPrint 4 3 26)
					)
					(local0
						(TimeHighPrint 4 3 27)
					)
					(else
						(TimeHighPrint 4 3 28)
					)
				)
			)
		)
	)
	
	(method (canBeHere)
		(return TRUE)
	)
)

(instance Net of PicView
	(properties
		y 125
		x 153
		view 3
		loop 6
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at]/net')
				(HighPrint 3 29)
			)
		)
	)
)

(instance soundSplash of Sound
	(properties
		number 53
		priority 1
	)
)

(instance splash of Prop
	(properties
		view 503
		loop 4
	)
)
