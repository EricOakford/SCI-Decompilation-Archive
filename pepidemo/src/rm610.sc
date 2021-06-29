;;; Sierra Script 1.0 - (do not remove this comment)
(script# 610)
(include game.sh) (include "610.shm")
(use Main)
(use BalloonTalker)
(use Print)
(use ForCount)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm610 0
	pTalker 1
	ljTalker 2
)

(local
	fromWhere
	local1
	local2
)
(instance rm610 of Room
	
	(method (init)
		(theIconBar disable:)
		(Bset 2)
		(super init: &rest)
		(Graph GFillRect 0 0 192 320 2 0)
		(curRoom overlay: 610)
		(Load RES_VIEW 610)
		(theMusic number: 941 setLoop: 1 play:)
		(flag init: stopUpd:)
		(if (not global199)
			(= global199 8)
		)
		(= fromWhere prevRoomNum)
		(curRoom setScript: animationScr)
	)
)

(instance flag of Prop
	(properties
		x 158
		y 114
		view 610
		priority 14
		signal fixPriOn
	)
)

(instance pepperGal of Actor
	(properties
		x 85
		y 146
		view 611
		priority 15
		signal fixPriOn
	)
)

(instance dogie of Actor
	(properties
		x 196
		y 147
		view 611
		loop 1
		priority 15
		signal fixPriOn
	)
)

(instance animationScr of Script
	
	(method (init)
		(super init: &rest)
		(if fromWhere
			(= register fromWhere)
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((and (== (theMusic prevSignal?) 10) (not local1))
				(= local1 1)
				(self cue:)
			)
			((and (== (theMusic prevSignal?) 20) (not local2))
				(= local2 1)
				(self cue:)
			)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (theGame handsOff:))
			(1
				(flag cycleSpeed: 8 setCycle: EndLoop)
			)
			(2
				(pepperGal init: setCycle: EndLoop self)
				(dogie init: setCycle: EndLoop self)
			)
			(3)
			(4
				(flag stopUpd: forceUpd:)
				(= cycles 2)
			)
			(5
				(messager say: 1 0 global199 1 2 self)
			)
			(6
				(pepperGal view: 613 setLoop: 0 cel: 0 setCycle: EndLoop self)
				(dogie view: 612 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(7)
			(8
				(pepperGal setLoop: 1 cel: 0 setCycle: BegLoop self)
				(dogie setLoop: 1 cel: 0)
				(messager say: 1 0 global199 3 self)
			)
			(9)
			(10
				(dogie setCycle: ForwardCounter 2)
				(messager say: 1 0 global199 4 self)
			)
			(11
				(pepperGal stopUpd:)
				(dogie setCycle: 0 stopUpd:)
				(= ticks 60)
			)
			(12
				(theGame setCursor: ARROW_CURSOR TRUE)
				(SetCursor 96 32)
				(repeat
					(switch
						(= temp0
							((Print new:)
								x: 54
								y: 11
								width: 200
								font: userFont
								addText: {Select your destination...}
								addButton: 1 {Try again!} 0 12
								addButton: 2 {Restore} 70 12
								addButton: 3 {Quit} 125 12
								init:
							)
						)
						(1 (= cycles 1))
						(2
							(if (theGame restore:) (-- state) (= cycles 2))
						)
						(3 (= quit 1) (self dispose:))
					)
					(if temp0 (break))
				)
			)
			(13
				(theIconBar enable:)
				(Bclr fCantSave)
				(curRoom newRoom: register)
			)
		)
	)
)

(instance pTalker of BalloonTalker
	(properties
		x 135
		y 70
		talkWidth 155
	)
)

(instance ljTalker of BalloonTalker
	(properties
		x 195
		y 100
		talkWidth 95
		font 61
	)
)
