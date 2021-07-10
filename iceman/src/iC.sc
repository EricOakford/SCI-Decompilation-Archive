;;; Sierra Script 1.0 - (do not remove this comment)
(script# 359)
(include game.sh)
(use Main)
(use hotelBar)
(use Intrface)
(use tahiti)
(use Follow)
(use Sight)
(use Motion)
(use Actor)
(use System)

(public
	iC 0
)

(instance iC of Actor
	(properties
		y 115
		x -10
		view 711
	)
	
	(method (init)
		(super init: &rest)
		(water init:)
		(self setCycle: copWalk setScript: iCS)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/cop,officer]>')
				(cond 
					((IsOffScreen self))
					((Said 'look[<at]')
						(Print 359 0)
					)
					((Said 'address')
						(Print 359 1)
					)
				)
			)
		)
	)
)

(instance iCS of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 10))
			(1
				(client show:)
				(proc11_2 0)
				(if
					(and
						(or
							(< (ego distanceTo: (ScriptID 11 1)) 25)
							(< (Abs (- (ego y?) (iC y?))) 20)
						)
						(== (ego view?) 206)
						(not (ego script?))
					)
					(HandsOff)
					(ego
						setMotion: egoMoveTo ((ScriptID 11 1) x?) (+ 20 ((ScriptID 11 1) y?))
					)
				)
				(iC setMotion: copMoveTo 115 115 self)
			)
			(2
				(iC view: 811 loop: 3 setCycle: copEndLoop self)
			)
			(3
				(water show: setCycle: copEndLoop)
				(= seconds 3)
			)
			(4
				(water hide:)
				((ScriptID 11 3) init:)
				((ScriptID 11 1) view: 811 loop: 5 cel: 0 posn: 145 114)
				(iC view: 711 setCycle: copWalk loop: 3)
				(= seconds 1)
			)
			(5
				(Print 359 2)
				((ScriptID 11 1)
					cycleSpeed: 1
					moveSpeed: 1
					setLoop: 0
					setCycle: lushWalk
					setMotion: lushMoveTo 60 114 self
				)
				(iC setMotion: theFollow (ScriptID 11 1) 30)
			)
			(6
				((ScriptID 11 1)
					loop: 1
					cycleSpeed: 1
					setCycle: copEndLoop self
				)
			)
			(7
				(iC setMotion: copMoveTo 50 115 self)
			)
			(8
				((ScriptID 11 1)
					loop: 2
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: lushWalk
					setMotion: theFollow iC 5
				)
				(if (and (== (ego view?) 206) (not (ego script?)))
					(HandsOn)
				)
				(iC setMotion: copMoveTo -10 115 self)
			)
			(9 (= cycles 5))
			(10
				(tahiti flags: (& (tahiti flags?) $ffef))
				(water dispose:)
				((ScriptID 11 1) dispose:)
				(iC dispose:)
			)
		)
	)
)

(instance water of Prop
	(properties
		y 108
		x 128
		view 811
		loop 4
		cycleSpeed 2
	)
	
	(method (delete)
		(super delete:)
		(DisposeScript 359)
	)
)

(instance egoMoveTo of MoveTo)

(instance copMoveTo of MoveTo)

(instance lushMoveTo of MoveTo)

(instance theFollow of Follow)

(instance copEndLoop of EndLoop)

(instance lushWalk of Walk)

(instance copWalk of Walk)
