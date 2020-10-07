;;; Sierra Script 1.0 - (do not remove this comment)
(script# 360)
(include game.sh)
(use Main)
(use Procs)
(use Tactor)
(use TScripts)
(use Intrface)
(use PolyPath)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)

(public
	Room360 0
)

(local
	[str 200]
)
(instance Room360 of FRoom
	(properties
		picture 360
		style HSHUTTER
	)
	
	(method (init)
		(= gameState 2)
		(self defeatEntrance: 1)
		(switch gameState
			(0
				(= picture 361)
				(LoadMany SOUND 100 101 105 106 108 115 116 117 118)
			)
			(2
				(LoadMany SOUND 5 108)
			)
		)
		(super init:)
		(theGame setScript: theZapCursor)
		(if (== gameState 2)
			(theMusic number: 5 priority: 15 setLoop: -1 play:)
		)
		(ego view: egoView)
		(LoadMany VIEW (+ 30 egoView) 720)
	)
	
	(method (dispose)
		(super dispose: &rest)
	)
	
	(method (cue)
		(super cue:)
		(switch gameState
			(0 (self setScript: begGame))
			(2 (self setScript: endGame))
		)
	)
	
	(method (enterSpecial)
		(if (== gameState 0)
			(ego normal: 1 hide:)
			(NormalEgo 8 egoView)
			(ego posn: 161 123 init:)
		)
		(self cue:)
	)
)

(instance begGame of HandsOffScript
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 125 116 self)
			)
			(1
				(ego setMotion: MoveTo 179 116 self)
			)
			(2
				(ego heading: 0)
				(president x: 149 y: 64 init: setCycle: CycleTo 4 1 self)
			)
			(3
				(sfx number: 115 play:)
				(president setCel: 5 setCycle: CycleTo 10 1 self)
			)
			(4
				(sfx number: 116 play:)
				(president setCel: 11 setCycle: EndLoop self)
			)
			(5
				(president stopUpd:)
				(book setCycle: CycleTo 3 1 self)
			)
			(6
				(sfx number: 100 play:)
				(book setCycle: CycleTo 6 1 self)
			)
			(7
				(sfx number: 101 play:)
				(book setCycle: EndLoop self)
			)
			(8
				(ego heading: 90 setMotion: MoveTo 201 116 self)
			)
			(9
				(Face ego book)
				(ego
					normal: 0
					view: (+ 40 egoView)
					loop: 0
					cel: 0
					cycleSpeed: 6
					setCycle: EndLoop
				)
				(sfx number: 118 play:)
				(bookWyrm
					posn: (+ (book x?) 37) (+ (book y?) 18)
					setLoop: 0
					setCel: 0
					init:
					setCycle: CycleTo 7 1 self
				)
			)
			(10
				(sfx number: 106 play:)
				(bookWyrm setCycle: EndLoop self)
			)
			(11 (ego setCycle: BegLoop self))
			(12
				(Say 2 bookWyrm bookWyrm -1 1 360 0)
				(Say 2 ego bookWyrm -1 1 360 1)
				(Say 0 bookWyrm bookWyrm -1 1 360 2)
				(Say 0 ego bookWyrm -1 1
					(Format @str 360 3 @userName)
				)
				(Say 0 bookWyrm bookWyrm -1 0
					(Format @str 360 4 @userName)
				)
				(Say 3 bookWyrm bookWyrm -1 0 360 5)
				(Say 3 bookWyrm bookWyrm -1 1 360 6)
				(Say 3 ego bookWyrm -1 1 360 7)
				(Say 0 bookWyrm bookWyrm self 1 360 8)
			)
			(13
				(RedrawCast)
				(sfx number: 105 play:)
				(bookWyrm
					setLoop: 0
					ignoreActors: 1
					setCycle: CycleTo 7 -1 self
				)
			)
			(14
				(sfx number: 117 play:)
				(bookWyrm setCycle: BegLoop self)
			)
			(15
				(NormalEgo 0 egoView)
				(ego
					normal: 1
					setMotion: PolyPath (+ (book x?) 37) (+ (book y?) 18) self
				)
			)
			(16
				(sfx number: 108 play:)
				(ego normal: 0 view: (+ 30 egoView) setCycle: EndLoop self)
			)
			(17
				(bookWyrm dispose:)
				(= cycles 1)
			)
			(18
				(if (theMusic handle?)
					(theMusic fade: 0 15 12 1)
				)
				(= ticks 120)
			)
			(19 (curRoom newRoom: 235))
		)
	)
)

(instance endGame of HandsOffScript
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 120))
			(1
				(Print 360 9
					#at 70 150
					#color 14
					#time 5
					#dispose
					self
				)
			)
			(2
				(ego init: normal: 0 hide:)
				((ego head?) hide:)
				(book posn: 160 82 cel: 9 init:)
				(sfx number: 108 play:)
				(egoLeap
					ignoreActors: 1
					view: (+ 30 egoView)
					cel: 15
					posn: 197 100
					init:
					setCycle: BegLoop self
				)
			)
			(3
				(egoLeap dispose:)
				(ego show: posn: 197 98 setMotion: MoveTo 173 98 self)
			)
			(4
				(ego
					normal: 0
					view: (+ 60 egoView)
					loop: 0
					cel: 0
					setCycle: CycleTo 3 1 self
				)
			)
			(5
				(book dispose:)
				(ego setCycle: EndLoop self)
			)
			(6
				(Say 2 ego 0 self 1 360 10)
			)
			(7 (= ticks 120))
			(8
				(Print 360 11
					#at 70 150
					#color 32
					#time 8
					#dispose
					self
				)
			)
			(9
				(theMusic fade: 0 15 12 1)
				(= ticks 120)
			)
			(10
				(= global89 2)
				(= quit TRUE)
			)
		)
	)
)

(instance bookWyrm of Tactor
	(properties
		view 757
		talkerID 18
	)
	
	(method (setUp)
		((= talkerObj bookWyrmTalkObj)
			setUp: bookWyrmBust bookWyrmEyes bookWyrmMouth
		)
	)
)

(instance bookWyrmTalkObj of TalkerObj
	(properties
		x -1
		y 97
		nsTop 10
		nsLeft 103
		view 751
	)
)

(instance bookWyrmBust of View
	(properties
		nsTop 15
		nsLeft 46
		view 751
		loop 6
	)
)

(instance bookWyrmEyes of Prop
	(properties
		nsTop 19
		nsLeft 49
		view 751
		loop 4
		cycleSpeed 36
	)
)

(instance bookWyrmMouth of Prop
	(properties
		nsTop 36
		nsLeft 46
		view 751
		loop 2
		cycleSpeed 12
	)
)

(instance teacher of Tactor
	(properties
		talkerID 20
	)
	
	(method (setUp)
		((= talkerObj teacherTalkObj)
			setUp: teacherBust teacherEyes teacherMouth
		)
	)
)

(instance teacherTalkObj of TalkerObj
	(properties
		x -1
		y 97
		nsTop 10
		nsLeft 108
		view 720
	)
)

(instance teacherBust of View
	(properties
		nsTop 31
		nsLeft 41
		view 720
		loop 6
	)
)

(instance teacherEyes of Prop
	(properties
		nsTop 27
		nsLeft 44
		view 720
		loop 4
		cycleSpeed 36
	)
)

(instance teacherMouth of Prop
	(properties
		nsTop 32
		nsLeft 40
		view 720
		loop 2
		cycleSpeed 12
	)
)

(instance book of Prop
	(properties
		x 109
		y 100
		view 360
		loop 2
	)
)

(instance page of Prop
	(properties
		x 297
		y 143
		view 360
		loop 1
	)
)

(instance president of Prop
	(properties
		x 200
		y 46
		view 360
	)
)

(instance egoLeap of Prop
	(properties
		view 30
	)
)

(instance everAfter of View
	(properties
		x 45
		y 8
		view 835
	)
)

(instance theEnd of View
	(properties
		x 84
		y 120
		view 835
		loop 1
	)
)

(instance sfx of Sound
	(properties
		flags mNOPAUSE
		number 115
	)
)
