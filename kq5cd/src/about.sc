;;; Sierra Script 1.0 - (do not remove this comment)
(script# 756)
(include game.sh)
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
	
	(method (doit &tmp printRet [versionBuf 255])
		(= global394 (DoAudio DACFound -1))
		(switch
			(= printRet
				(Print 756 0
					#font USERFONT
					#button {About KQV} 1
					#button {Help} 2
					#button {Cancel} 0
				)
			)
			(1
				(Format @versionBuf 756 1 version)
				(Print @versionBuf #mode teJustCenter)
				(Print 756 2 #width 210)
				(Print 756 3 #width 210)
				(Print 756 4 #width 210)
				(Print 756 5 #width 210)
				(Print 756 6 #width 210)
				(Print 756 7)
				(Print 756 8 #width 210)
				(Print 756 9 #width 210)
				((File new:)
					name: {HELP.TXT}
					read: @str 100
					close:
					dispose:
				)
				(Printf 756 10 @str)
				(DisposeScript FILE)
			)
			(2 ((ScriptID 753) doit:))
		)
	)
)
