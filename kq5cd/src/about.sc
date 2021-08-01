;;; Sierra Script 1.0 - (do not remove this comment)
(script# 756)
(include sci.sh)
(use Main)
(use Intrface)
(use File)
(use System)

(public
	about 0
)

(local
	[str 100]
)
(instance about of Code
	(properties)
	
	(method (doit &tmp temp0 [temp1 255])
		(= global394 (DoAudio 9 -1))
		(switch
			(= temp0
				(Print
					756
					0
					#font
					1
					#button
					{About KQV}
					1
					#button
					{Help}
					2
					#button
					{Cancel}
					0
				)
			)
			(1
				(Format @temp1 756 1 version)
				(Print @temp1 #mode 1)
				(Print 756 2 #width 210)
				(Print 756 3 #width 210)
				(Print 756 4 #width 210)
				(Print 756 5 #width 210)
				(Print 756 6 #width 210)
				(Print 756 7)
				(Print 756 8 #width 210)
				(Print 756 9 #width 210)
				((Class_993_0 new:)
					name: {HELP.TXT}
					read: @str 100
					close:
					dispose:
				)
				(Printf 756 10 @str)
				(DisposeScript 993)
			)
			(2 ((ScriptID 753) doit:))
		)
	)
)
