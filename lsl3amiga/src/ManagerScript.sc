;;; Sierra Script 1.0 - (do not remove this comment)
(script# 421)
(include game.sh)
(use Main)
(use n021)
(use Intrface)
(use Motion)
(use System)

(public
	ManagerScript 0
)

(local
	local0
	[msgBuf 40]
	[titleBuf 22]
)
(instance ManagerScript of Script
	(method (changeState newState)
		(ChangeScriptState self newState 4 2)
		(switch (= state newState)
			(0
				(Load VIEW 423)
				(Load SOUND 6)
				(Load SOUND 3)
				(Load SOUND 12)
			)
			(1
				(Print 421 0)
				(theDoor setCycle: EndLoop self)
				(music number: 6 loop: -1 play:)
			)
			(2
				(theDoor stopUpd:)
				(client setPri: 9 setCycle: Forward posn: 33 143)
				(= seconds 3)
			)
			(3
				(Print 421 1 #at 10 5 #width 290)
				(Print 421 2 #at -1 144)
				(client loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(4
				(ego hide:)
				(client loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(5
				(Print 421 3 #at -1 10 #dispose)
				(theGame setSpeed: 1)
				(= seconds 3)
			)
			(6
				(cls)
				(client
					loop: 3
					cycleSpeed: (Random 0 2)
					cel: 0
					setCycle: EndLoop self
				)
				(if (>= 10 (++ local0))
					(-- state)
				)
			)
			(7
				(client loop: 4 cel: 0 setCycle: EndLoop self)
			)
			(8
				(ego
					view: 421
					setLoop: 5
					posn: 60 128
					setStep: 20 9
					setPri: 11
					setMotion: MoveTo 312 68 self
					show:
				)
				(music stop: number: 3 loop: 1 play:)
				(client loop: 0 cel: 0 stopUpd:)
				(curRoom north: 0 east: 0)
			)
			(9
				(music stop: number: 12 loop: 1 play:)
				(client hide:)
				(theDoor setCycle: BegLoop self)
			)
			(10
				(theGame setScript: (ScriptID DYING))
				((ScriptID DYING)
					caller: 423
					register: (Format @msgBuf 421 4)
					next: (Format @titleBuf 421 5)
				)
				(self dispose:)
			)
		)
	)
)
