;;; Sierra Script 1.0 - (do not remove this comment)
(script# 694)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room694 0
)

(local
	valanice
	alex
	grahamCloseup
	grahamFace
	rosella
	rosellaBody
	local6
	local7
)
(instance Room694 of Room
	(properties
		picture 204
		style DISSOLVE
	)
	
	(method (init)
		(super init:)
		(self setRegions: END)
		(= number curRoomNum)
		(curRoom setScript: actions)
	)
)

(instance actions of Script
	(properties)
	
	(method (changeState newState &tmp [secretCode 12])
		(switch (= state newState)
			(0
				(= local6 1)
				(self changeState: 500)
			)
			(1
				(if (ego has: iMagicFruit)
					(self changeState: 100)
				else
					((View new:)
						view: 786
						loop: 4
						cel: 0
						posn: 277 171
						setPri: 13
						ignoreActors:
						init:
						addToPic:
					)
					((= rosella (Prop new:))
						view: 786
						loop: 5
						cel: 0
						posn: 277 138
						setPri: 14
						init:
					)
					(= seconds 6)
				)
			)
			(2
				(cast eachElementDo: #dispose)
				(addToPics eachElementDo: #dispose)
				(curRoom drawPic: 991)
				(self changeState: 3)
			)
			(3
				((= grahamCloseup (View new:))
					view: 752
					loop: 1
					cel: 0
					posn: 159 114
					setPri: 14
					init:
					addToPic:
				)
				(= timedMessage (Print 694 0 #at -1 117 #dispose))
				(= grahamFace (Prop new:))
				(grahamFace
					view: 752
					loop: 5
					cel: 2
					posn: 164 88
					cycleSpeed: 18
					setPri: 15
					init:
					setCycle: BegLoop self
				)
			)
			(4
				(grahamFace
					view: 752
					loop: 5
					cel: 0
					posn: 164 88
					cycleSpeed: 8
					setPri: 15
					init:
					setCycle: EndLoop self
				)
				(= seconds 12)
			)
			(5
				(cls)
				(cast eachElementDo: #dispose)
				(addToPics eachElementDo: #dispose)
				(grahamFace dispose:)
				(= local6 6)
				(self changeState: 500)
			)
			(6
				((View new:)
					view: 786
					loop: 4
					cel: 0
					posn: 277 171
					setPri: 13
					ignoreActors:
					init:
					addToPic:
				)
				((= rosella (Prop new:))
					view: 786
					loop: 5
					cel: 0
					posn: 277 138
					setPri: 14
					init:
				)
				(= seconds 3)
			)
			(7
				(= timedMessage
					(Print 694 1 #at -1 25 #width 240 #dispose)
				)
				(= seconds 2)
				(valanice cel: 1)
			)
			(8
				(rosella cel: 3)
				(alex cel: 1)
				(if (== ((ScriptID 521 1) state?) 3)
					((ScriptID 521 1) client: actions)
					((ScriptID 0 4) setReal: self 10)
				else
					(= seconds 10)
				)
			)
			(9
				(if
					(or
						(timers contains: (ScriptID 0 4))
						(== ((ScriptID END 1) state?) 3)
					)
					(-- state)
				else
					(self changeState: 10)
				)
			)
			(10 (= dead TRUE) (cls))
			(100
				(= rosellaBody (Actor new:))
				(= rosella (Actor new:))
				(rosella
					view: 786
					setLoop: 5
					cel: 2
					posn: 298 141
					setPri: 13
					ignoreActors:
					setMotion: MoveTo 250 142
					init:
				)
				(rosellaBody
					view: 786
					setLoop: 4
					cel: 0
					posn: 298 174
					setPri: 12
					setMotion: MoveTo 250 175 self
					init:
				)
			)
			(101
				(rosella cel: 4)
				(ego put: 25 999)
				(= timedMessage
					(Print 694 2 #at -1 35 #width 240 #dispose)
				)
				(= seconds 8)
			)
			(102
				(cls)
				(cast eachElementDo: #dispose)
				(addToPics eachElementDo: #dispose)
				(curRoom drawPic: 991)
				((View new:)
					view: 752
					loop: 1
					cel: 0
					posn: 159 114
					setPri: 14
					init:
					addToPic:
				)
				(= grahamFace (Prop new:))
				(grahamFace
					view: 752
					setLoop: 6
					cel: 0
					posn: 164 88
					cycleSpeed: 10
					setCycle: Forward
					setPri: 15
					init:
				)
				(= seconds 8)
			)
			(103
				(grahamFace
					setLoop: 7
					cel: 0
					cycleSpeed: 18
					setCycle: EndLoop self
				)
			)
			(104
				(grahamFace dispose:)
				((View new:)
					view: 786
					loop: 2
					cel: 0
					posn: 85 177
					setPri: 11
					ignoreActors:
					init:
					addToPic:
				)
				((= valanice (Prop new:))
					view: 786
					loop: 3
					cel: 2
					posn: 85 147
					setPri: 12
					init:
					stopUpd:
				)
				((View new:)
					view: 786
					loop: 0
					cel: 0
					posn: 228 176
					setPri: 11
					ignoreActors:
					init:
					addToPic:
				)
				((= alex (Prop new:))
					view: 786
					loop: 1
					cel: 2
					posn: 228 101
					setPri: 12
					init:
					stopUpd:
				)
				((View new:)
					view: 752
					loop: 0
					cel: 1
					posn: 163 137
					setPri: 14
					ignoreActors:
					init:
					addToPic:
				)
				((View new:)
					view: 786
					setLoop: 5
					cel: 4
					posn: 250 141
					setPri: 13
					ignoreActors:
					init:
					addToPic:
				)
				((View new:)
					view: 786
					setLoop: 4
					cel: 0
					posn: 250 174
					setPri: 12
					init:
					addToPic:
				)
				((View new:)
					view: 752
					loop: 2
					cel: 0
					posn: 204 89
					setPri: 6
					init:
					addToPic:
				)
				((View new:)
					view: 752
					loop: 2
					cel: 0
					posn: 123 89
					setPri: 6
					init:
					addToPic:
				)
				((Prop new:)
					view: 752
					loop: 4
					cel: 1
					posn: 204 63
					setPri: 14
					ignoreActors:
					cycleSpeed: 1
					setCycle: Forward
					init:
				)
				((Prop new:)
					view: 752
					loop: 4
					cel: 0
					posn: 123 63
					setPri: 14
					ignoreActors:
					cycleSpeed: 1
					setCycle: Forward
					init:
				)
				((View new:)
					view: 752
					loop: 3
					cel: 0
					posn: 64 139
					setPri: 0
					init:
					addToPic:
				)
				(curRoom drawPic: 204)
				(= seconds 4)
			)
			(105
				(= timedMessage
					(Print 694 3
						#at -1 20
						#width 240
						#title {Rosella}
						#dispose
					)
				)
				(= seconds 7)
			)
			(106
				(cls)
				(= timedMessage
					(Print 694 4
						#at -1 20
						#width 240
						#title {King Graham}
						#dispose
					)
				)
				(= seconds 7)
			)
			(107
				(cls)
				(= timedMessage
					(Print 694 5
						#at -1 20
						#width 240
						#title {Rosella}
						#dispose
					)
				)
				(= seconds 8)
			)
			(108
				(cls)
				(cast eachElementDo: #dispose)
				(addToPics eachElementDo: #dispose)
				(curRoom drawPic: 991)
				((= grahamFace (View new:))
					view: 752
					setLoop: 0
					cel: 3
					posn: 159 114
					setPri: 14
					init:
				)
				(= timedMessage
					(Print 694 6
						#at -1 138
						#width 240
						#title {Rosella}
						#dispose
					)
				)
				(= seconds 15)
			)
			(109
				(cls)
				(grahamFace dispose:)
				((View new:)
					view: 786
					loop: 2
					cel: 0
					posn: 85 177
					setPri: 11
					ignoreActors:
					init:
					addToPic:
				)
				((= valanice (Prop new:))
					view: 786
					loop: 3
					cel: 2
					posn: 85 147
					setPri: 12
					init:
					stopUpd:
				)
				((View new:)
					view: 786
					loop: 0
					cel: 0
					posn: 228 176
					setPri: 11
					ignoreActors:
					init:
					addToPic:
				)
				((= alex (Prop new:))
					view: 786
					loop: 1
					cel: 2
					posn: 228 101
					setPri: 12
					init:
					stopUpd:
				)
				((View new:)
					view: 752
					loop: 0
					cel: 1
					posn: 163 137
					setPri: 14
					ignoreActors:
					init:
					addToPic:
				)
				((View new:)
					view: 752
					loop: 0
					cel: 2
					posn: 158 82
					ignoreActors:
					setPri: 15
					init:
				)
				((View new:)
					view: 786
					setLoop: 4
					cel: 0
					posn: 250 174
					setPri: 12
					init:
					addToPic:
				)
				((View new:)
					view: 786
					setLoop: 5
					cel: 4
					posn: 250 141
					setPri: 13
					ignoreActors:
					init:
					addToPic:
				)
				((View new:)
					view: 752
					loop: 2
					cel: 0
					posn: 204 89
					setPri: 6
					init:
					addToPic:
				)
				((View new:)
					view: 752
					loop: 2
					cel: 0
					posn: 123 89
					setPri: 6
					init:
					addToPic:
				)
				((Prop new:)
					view: 752
					loop: 4
					cel: 1
					posn: 204 63
					setPri: 14
					ignoreActors:
					cycleSpeed: 1
					setCycle: Forward
					init:
				)
				((Prop new:)
					view: 752
					loop: 4
					cel: 0
					posn: 123 63
					setPri: 14
					ignoreActors:
					cycleSpeed: 1
					setCycle: Forward
					init:
				)
				((View new:)
					view: 752
					loop: 3
					cel: 0
					posn: 64 139
					setPri: 0
					init:
					addToPic:
				)
				(curRoom drawPic: 204)
				(if (== ((ScriptID 521 1) state?) 3)
					((ScriptID 521 1) client: actions)
					((ScriptID 0 4) setReal: self 5)
				else
					(= seconds 5)
				)
			)
			(110
				(if
					(or
						(timers contains: (ScriptID 0 4))
						(== ((ScriptID 521 1) state?) 3)
					)
					(-- state)
				else
					(self changeState: 111)
				)
			)
			(111
				(Print 694 7 #at -1 85 #icon 100 0 0 #dispose)
				(= gameHours 0)
				(= seconds 10)
			)
			(112 (cls))
			(500
				((View new:)
					view: 786
					loop: 2
					cel: 0
					posn: 85 177
					setPri: 11
					ignoreActors:
					init:
					addToPic:
				)
				((View new:)
					view: 786
					loop: 0
					cel: 0
					posn: 228 176
					setPri: 11
					ignoreActors:
					init:
					addToPic:
				)
				((View new:)
					view: 752
					loop: 0
					cel: 0
					posn: 163 137
					setPri: 15
					ignoreActors:
					init:
					addToPic:
				)
				((= alex (Prop new:))
					view: 786
					loop: 1
					cel: 0
					posn: 228 101
					setPri: 12
					init:
					stopUpd:
				)
				((= valanice (Prop new:))
					view: 786
					loop: 3
					cel: 0
					posn: 85 147
					setPri: 12
					init:
					stopUpd:
				)
				((View new:)
					view: 752
					loop: 2
					cel: 0
					posn: 204 89
					setPri: 6
					init:
					addToPic:
				)
				((View new:)
					view: 752
					loop: 2
					cel: 0
					posn: 123 89
					setPri: 6
					init:
					addToPic:
				)
				((Prop new:)
					view: 752
					loop: 4
					cel: 1
					posn: 204 63
					setPri: 14
					ignoreActors:
					cycleSpeed: 1
					setCycle: Forward
					init:
				)
				((Prop new:)
					view: 752
					loop: 4
					cel: 0
					posn: 123 63
					setPri: 14
					ignoreActors:
					cycleSpeed: 1
					setCycle: Forward
					init:
				)
				((View new:)
					view: 752
					loop: 3
					cel: 0
					posn: 64 139
					setPri: 0
					init:
					addToPic:
				)
				(curRoom drawPic: 204)
				(self changeState: local6)
			)
			;EO: This state was used in earlier versions, but was rendered inaccessible later on,
			;since the promotion it was for had since ended. However, it's still present here.
			(600
				(StrAt @secretCode 10)
				(= local7 0)
				(while (<= local7 9)
					(StrAt @secretCode local7 (+ (Random 0 25) 65))
					(++ local7)
				)
				(StrAt @secretCode 10 0)
				(if (== score 230)
					(StrAt @secretCode 2 69)
					(StrAt @secretCode 6 52)
					(StrAt @secretCode 9 65)
				)
				(Print
					(Format @str {Your Secret Code is:\n\n___%s} @secretCode)
				)
			)
		)
	)
)
