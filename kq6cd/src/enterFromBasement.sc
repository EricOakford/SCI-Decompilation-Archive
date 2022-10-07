;;; Sierra Script 1.0 - (do not remove this comment)
(script# 732)
(include sci.sh)
(use Main)
(use Conv)
(use Motion)
(use Actor)
(use System)

(public
	enterFromBasement 0
)

(instance enterFromBasement of Script
	(properties)
	
	(method (init)
		(theGame handsOff:)
		(= register ((ScriptID 80 0) tstFlag: 709 2))
		(if (not register)
			((ScriptID 1015 6) talkWidth: 150 x: 15 y: 20)
			((ScriptID 1015 7) talkWidth: 135 x: 160 y: 20)
			((ScriptID 80 5) loop: 3 cel: 0 posn: 151 154 init:)
			((ScriptID 80 6) loop: 0 cel: 0 posn: 133 146 init:)
			(theMusic fadeTo: 700 -1)
		else
			(theMusic fade: 127 5 10 0)
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 732)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 10))
			(1
				(soundFx2 number: 901 loop: 1 play:)
				(if (not register) (= state 4))
				((ScriptID 730 2) setCycle: CycleTo 3 1 self)
			)
			(2
				(EgoHead init:)
				(= seconds 3)
			)
			(3 (messager say: 1 0 9 1 self))
			(4
				(EgoHead dispose:)
				(= seconds 2)
			)
			(5
				((ScriptID 730 2) setCycle: EndLoop self)
			)
			(6
				(soundFx2 stop:)
				(ego setMotion: MoveTo 233 144 self)
			)
			(7
				((ScriptID 730 2) setCycle: BegLoop self)
			)
			(8
				(soundFx2 number: 902 loop: 1 play:)
				((ScriptID 730 2) stopUpd:)
				(if (not register)
					(self setScript: guardsCaptureEgo)
				else
					(messager say: 1 0 9 2 self)
				)
			)
			(9
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance guardsCaptureEgo of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic stop:)
				(theGlobalSound number: 710 loop: -1 play:)
				(roomConv
					add: -1 1 0 7 1
					add: -1 1 0 7 2
					add: -1 1 0 7 3
					init: self
				)
			)
			(1
				(curRoom moveOtherGuard: 1)
				((ScriptID 80 5) setScript: (ScriptID 80 4) self 1)
			)
			(2
				(messager say: 1 0 7 4 self oneOnly: 0)
			)
			(3
				(theGlobalSound fade:)
				((ScriptID 80 0) setFlag: 709 8192)
				(curRoom newRoom: 820)
			)
		)
	)
)

(instance roomConv of Conversation
	(properties)
)

(instance EgoHead of View
	(properties
		x 248
		y 113
		view 730
		loop 5
	)
)
