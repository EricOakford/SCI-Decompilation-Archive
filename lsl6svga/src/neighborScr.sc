;;; Sierra Script 1.0 - (do not remove this comment)
(script# 623)
(include sci.sh)
(use Main)
(use fileScr)
(use cObj_610)
(use String)
(use Sound)
(use System)

(public
	neighborScr 0
	sexLineScr 1
)

(local
	local0
)
(instance neighborScr of Script
	(properties)
	
	(method (dispose)
		(sfx dispose:)
		(sfx2 dispose:)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic2 number: 0 stop:)
				(theMusic fade: 95 10 10 0)
				(= cycles 2)
			)
			(1
				(switch (++ global196)
					(5
						(self setScript: poundScr1 self)
					)
					(6
						(self setScript: poundScr6 self)
					)
					(1
						(self setScript: poundScr3 self)
					)
					(2
						(self setScript: poundScr4 self)
					)
					(3
						(self setScript: poundScr5 self)
					)
					(4
						(self setScript: poundScr2 self)
					)
					(else 
						(-- state)
						(= global196 0)
						(= cycles 2)
					)
				)
			)
			(2
				(theMusic fade: 127 10 10 0)
				(= cycles 2)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poundScr1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 6)
				(proc79_11 1004 144 421)
				(= cycles 2)
			)
			(1
				(switch register
					(6
						(sfx number: 1004 loop: 1 play:)
					)
					(4
						(sfx number: 144 loop: 1 play:)
					)
					(2
						(sfx number: 1004 loop: 1 play:)
					)
				)
				(= ticks 90)
			)
			(2
				(if (-- register)
					(= state (- state 2))
				else
					(sfx2 number: 0 stop:)
					(sfx number: 0 stop:)
				)
				(= ticks 10)
			)
			(3
				(sfx number: 0 dispose:)
				(sfx2 number: 0 dispose:)
				(proc79_12 1004 144 421)
				(self dispose:)
			)
		)
	)
)

(instance poundScr2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc79_11 908 657 652 910)
				(= register 6)
				(= cycles 2)
			)
			(1
				(cond 
					((OneOf register 6 5 4) (sfx number: 908 loop: 1 play:))
					((== register 3) (sfx number: 657 loop: 1 play:))
					((OneOf register 1 2) (sfx number: 652 loop: 1 play:))
					((not register) (sfx number: 910 loop: 1 play:))
				)
				(= ticks 40)
			)
			(2
				(if (!= (-- register) -1) (= state (- state 2)))
				(= ticks 10)
			)
			(3
				(sfx number: 0 dispose:)
				(sfx2 number: 0 dispose:)
				(proc79_12 908 657 652 910)
				(self dispose:)
			)
		)
	)
)

(instance poundScr3 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc79_11 913 144 421 912)
				(= register 4)
				(= cycles 2)
			)
			(1
				(switch register
					(4
						(sfx number: 913 loop: 1 play:)
					)
					(3
						(sfx number: 913 loop: 1 play:)
					)
					(2
						(sfx number: 144 loop: 1 play:)
					)
					(1
						(sfx number: 912 loop: 1 play:)
					)
				)
				(= ticks 60)
			)
			(2
				(if (-- register)
					(= state (- state 2))
				else
					(sfx2 number: 0 stop:)
					(sfx number: 0 stop:)
				)
				(= ticks 10)
			)
			(3
				(sfx number: 0 dispose:)
				(sfx2 number: 0 dispose:)
				(proc79_12 913 144 421 912)
				(self dispose:)
			)
		)
	)
)

(instance poundScr4 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 6)
				(proc79_11 325 386 911)
				(sfx2 number: 325 loop: -1 play:)
				(= cycles 2)
			)
			(1
				(switch register
					(5
						(sfx number: 386 loop: 1 play:)
					)
					(3
						(sfx number: 911 loop: 1 play:)
					)
					(1
						(sfx number: 386 loop: 1 play:)
					)
				)
				(= ticks 90)
			)
			(2
				(if (-- register)
					(= state (- state 2))
				else
					(sfx2 number: 0 stop:)
					(sfx number: 0 stop:)
				)
				(= ticks 10)
			)
			(3
				(sfx number: 0 dispose:)
				(sfx2 number: 0 dispose:)
				(proc79_12 325 386 911)
				(self dispose:)
			)
		)
	)
)

(instance poundScr5 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc79_11 908 911 1004)
				(= register 10)
				(= cycles 2)
			)
			(1
				(cond 
					((OneOf register 10 9 8 6 5 3 2 1) (sfx number: 908 loop: 1 play:))
					((OneOf register 7 0) (sfx number: 911 loop: 1 play:))
					((== register 4) (sfx number: 1004 loop: 1 play:))
				)
				(= ticks 90)
			)
			(2
				(if (!= (-- register) -1) (= state (- state 2)))
				(= ticks 10)
			)
			(3
				(sfx number: 0 dispose:)
				(sfx2 number: 0 dispose:)
				(proc79_12 908 911 1004)
				(self dispose:)
			)
		)
	)
)

(instance poundScr6 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc79_11 907 909 901 910)
				(= register 5)
				(= cycles 2)
			)
			(1
				(switch register
					(5
						(sfx number: 907 loop: 1 play:)
					)
					(4
						(sfx number: 909 loop: 1 play:)
					)
					(3
						(sfx number: 901 loop: 1 play:)
					)
					(2
						(sfx number: 910 loop: 1 play:)
					)
					(1
						(sfx number: 907 loop: 1 play:)
					)
					(0
						(sfx number: 901 loop: 1 play:)
					)
				)
				(= ticks 90)
			)
			(2
				(if (!= (-- register) -1) (= state (- state 2)))
				(= ticks 30)
			)
			(3
				(sfx number: 0 dispose:)
				(sfx2 number: 0 dispose:)
				(proc79_12 907 909 901 910)
				(self dispose:)
			)
		)
	)
)

(instance sexLineScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(Bset 97)
				(messager say: 1 4 67 0 self 610)
			)
			(1
				((ScriptID 610 0) init: self)
			)
			(2
				(switch register
					(1
						(theGame handsOff:)
						(messager say: 1 4 71 1 self 610)
					)
					(-1 (Bclr 97) (self dispose:))
					(else 
						(= state 0)
						(theGame handsOff:)
						(messager say: 1 4 70 0 self 610)
					)
				)
			)
			(3
				((ScriptID 610 0) init: self)
			)
			(4
				(switch register
					(5 (= state 19) (= ticks 90))
					(else 
						(messager say: 1 4 71 2 self 610)
					)
				)
			)
			(5
				(Bset 57)
				(proc610_2)
				(proc610_3)
				(self dispose:)
			)
			(20
				(messager say: 1 4 88 0 self 610)
			)
			(21 (= ticks 90))
			(22
				(= temp0 (Str new: 255))
				(Message 0 0 7 0 0 16 (temp0 data?))
				(SetQuitStr (temp0 data?))
				(= quit 1)
			)
		)
	)
)

(instance sfx2 of Sound
	(properties)
)

(instance sfx of Sound
	(properties)
)
